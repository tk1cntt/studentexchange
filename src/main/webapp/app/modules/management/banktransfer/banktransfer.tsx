import React from 'react';

import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';
import qs from 'query-string';
import { Input, Modal } from 'antd';
import NumberFormat from 'react-number-format';

import { getSession } from 'app/shared/reducers/authentication';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';
import Footer from 'app/shared/layout/footer/footer';

import { getUserByMobile, reset } from 'app/entities/user-profile/user-profile.reducer';
import { createEntity as createUserBalance } from 'app/entities/user-balance/user-balance.reducer';

export interface IBankTransferProp extends StateProps, DispatchProps {
  location: any;
  history: any;
}

export interface IBankTransferState {
  showPayment: boolean;
  topupMobile: string;
  topupAmount: number;
  mobile: any;
}

export class BankTransfer extends React.Component<IBankTransferProp> {
  state: IBankTransferState = {
    showPayment: false,
    topupMobile: null,
    topupAmount: null,
    mobile: null
  };

  componentWillUnmount() {
    this.props.reset();
  }

  onChangeMobile = e => {
    this.setState({
      mobile: e.target.value
    });
  };

  searchClick = () => {
    this.props.getUserByMobile('mobile.contains=' + this.state.mobile);
  };

  showPaymentConfirm = topupMobile => {
    this.setState({
      showPayment: true,
      topupMobile
    });
  };

  onChangeTopupAmount = e => {
    this.setState({
      topupAmount: e.target.value
    });
  };

  onChangeMoney = values => {
    const { formattedValue, value } = values;
    this.setState({
      money: formattedValue
    });
    this.setState({
      topupAmount: value
    });
  };

  topupClick = () => {
    const entity = {
      createByLogin: this.state.topupMobile,
      cash: this.state.topupAmount
    };
    this.props.createUserBalance(entity);
    this.setState({
      showPayment: false,
      topupMobile: null,
      topupAmount: null,
      mobile: null
    });
  };

  topupCancel = () => {
    this.setState({
      showPayment: false
    });
  };

  render() {
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="manager-management" activeSubMenu="banktransfer" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row  border-bottom white-bg dashboard-header">
            <label>Số điện thoại</label>
            <div className="input-group">
              <input
                type="text"
                placeholder="Nhập số điện thoại cần tìm"
                name="mobile"
                className="form-control form-control-lg"
                onChange={this.onChangeMobile}
              />
              <div className="input-group-btn">
                <button className="btn btn-primary" type="button" onClick={this.searchClick}>
                  <i className="fa fa-search" /> Tìm kiếm
                </button>
              </div>
            </div>
          </div>
          <div className="row wrapper wrapper-content">
            <div className="row">
              <div className="col-lg-12">
                <div className="ibox">
                  <div className="ibox-content">
                    {this.props.userProfileList.length === 0 ? (
                      <div className="no-content">Không có dữ liệu</div>
                    ) : (
                      <table
                        className="footable table table-stripped toggle-arrow-tiny tablet breakpoint footable-loaded"
                        data-page-size={15}
                      >
                        <thead>
                          <tr>
                            <th className="footable-visible footable-first-column footable-sortable">Số điện thoại</th>
                            <th className="footable-visible footable-sortable">Họ tên</th>
                            <th className="footable-visible footable-sortable">Thành phố</th>
                            <th className="footable-visible footable-sortable">Quận huyện</th>
                            <th className="footable-visible footable-sortable" />
                          </tr>
                        </thead>
                        <tbody>
                          {this.props.userProfileList.map((userProfile, i) => (
                            <tr key={`id-${i}`} className="footable-even" style={{}}>
                              <td className="footable-visible footable-first-column">
                                <span className="footable-toggle" />
                                {userProfile.mobile}
                              </td>
                              <td className="footable-visible">{userProfile.fullName}</td>
                              <td className="footable-visible">{userProfile.cityName}</td>
                              <td className="footable-visible">{userProfile.districtName}</td>
                              <td className="footable-visible">
                                <div className="input-group-btn">
                                  <button
                                    className="btn btn-xs btn-danger"
                                    type="button"
                                    onClick={this.showPaymentConfirm.bind(this, userProfile.mobile)}
                                  >
                                    <i className="fa fa-sign-in" /> Nạp tiền
                                  </button>
                                </div>
                              </td>
                            </tr>
                          ))}
                        </tbody>
                      </table>
                    )}
                  </div>
                </div>
              </div>
            </div>{' '}
          </div>
          {this.state.showPayment ? (
            <Modal
              title={`Nạp tiền cho tài khoản ${this.state.topupMobile}`}
              visible={this.state.showPayment}
              okText="Nạp tiền"
              okType="danger"
              cancelText="Hủy"
              onOk={this.topupClick}
              onCancel={this.topupCancel}
            >
              <p>Hãy xác nhận lại thông tin trước khi thực hiện nạp tiền</p>
              <div className="form-group">
                <label>Số tiền</label>
                <NumberFormat
                  value={this.state.topupAmount}
                  displayType={'input'}
                  customInput={Input}
                  thousandSeparator
                  onValueChange={this.onChangeMoney}
                />
              </div>
            </Modal>
          ) : (
            ''
          )}
          <Footer />
        </div>
      </>
    );
  }
}

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  currencyRateEntity: storeState.currencyRate.entity,
  userBalanceEntity: storeState.userBalance.entity,
  userProfileList: storeState.userProfile.entities,
  isAuthenticated: storeState.authentication.isAuthenticated
});

const mapDispatchToProps = { getSession, reset, getUserByMobile, createUserBalance };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BankTransfer);
