import React from 'react';

import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';
import { TextFormat } from 'react-jhipster';

import { getSession } from 'app/shared/reducers/authentication';
import { getEntities as getAllPayment } from 'app/entities/payment/payment.reducer';
import { formatCurency } from 'app/shared/util/utils';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';
import Footer from 'app/shared/layout/footer/footer';
import { PaymentType } from 'app/shared/model/payment.model';
import { APP_DATE_FORMAT } from 'app/config/constants';

export interface IHomeProp extends StateProps, DispatchProps {}

export interface ICheckoutProp extends StateProps, DispatchProps {
  location: any;
  history: any;
}

export class Payment extends React.Component<ICheckoutProp> {
  componentDidMount() {
    this.props.getAllPayment(0, 20, 'createAt,desc');
  }

  render() {
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="payment-management" activeSubMenu="history" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row  border-bottom white-bg dashboard-header">
            <h4>Thông tin giao dịch</h4>
          </div>
          <div className="row">
            <div className="col-lg-12">
              <div className="ibox">
                <div className="ibox-content">
                  {this.props.paymentList.length === 0 ? (
                    <div className="no-content">Không có dữ liệu</div>
                  ) : (
                    <table
                      className="footable table table-stripped toggle-arrow-tiny tablet breakpoint footable-loaded"
                      data-page-size={15}
                    >
                      <thead>
                        <tr>
                          <th>Khách hàng</th>
                          <th>Mã giao dịch</th>
                          <th>Số tiền</th>
                          <th>Số dư sau giao dịch</th>
                          <th>Loại giao dịch</th>
                          <th>Nội dung</th>
                        </tr>
                      </thead>
                      <tbody>
                        {this.props.paymentList.map((payment, i) => (
                          <tr key={`id-${i}`} className="footable-even" style={{}}>
                            <td className="footable-visible footable-first-column">{payment.customerLogin}</td>
                            <td className="footable-visible footable-first-column">
                              {payment.code}
                              <br />
                              <small>
                                <TextFormat type="date" value={payment.createAt} format={APP_DATE_FORMAT} />
                              </small>
                            </td>
                            <td>
                              {payment.type === PaymentType.ORDER_PAYMENT ? (
                                <b className="text-danger">-{formatCurency(payment.amount)}đ</b>
                              ) : (
                                <b className="text-info">+{formatCurency(payment.amount)}đ</b>
                              )}
                            </td>
                            <td>{formatCurency(payment.newBalance)}đ</td>
                            <td>{payment.type}</td>
                            <td>{payment.note}</td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  )}
                </div>
              </div>
            </div>
          </div>
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
  paymentList: storeState.payment.entities,
  isAuthenticated: storeState.authentication.isAuthenticated
});

const mapDispatchToProps = { getSession, getAllPayment };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Payment);
