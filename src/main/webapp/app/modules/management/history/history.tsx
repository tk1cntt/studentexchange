import React from 'react';

import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';

import { getSession } from 'app/shared/reducers/authentication';
import { getEntities as getAllPayment } from 'app/entities/payment/payment.reducer';
import { formatCurency } from 'app/shared/util/utils';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';
import Footer from 'app/shared/layout/footer/footer';
import { PaymentType } from 'app/shared/model/payment.model';

export interface IHomeProp extends StateProps, DispatchProps {}

export interface ICheckoutProp extends StateProps, DispatchProps {
  location: any;
  history: any;
}

export class Payment extends React.Component<ICheckoutProp> {
  componentDidMount() {
    this.props.getAllPayment();
  }

  render() {
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="manager-management" activeSubMenu="history" />
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
                          <th className="footable-visible footable-sortable">Khách hàng</th>
                          <th className="footable-visible footable-sortable">Mã giao dịch</th>
                          <th className="footable-visible footable-sortable">Số tiền</th>
                          <th className="footable-visible footable-sortable">Số dư sau giao dịch</th>
                          <th className="footable-visible footable-sortable">Loại giao dịch</th>
                          <th className="footable-visible footable-sortable">Nội dung</th>
                          <th className="footable-visible footable-sortable">Trạng thái</th>
                        </tr>
                      </thead>
                      <tbody>
                        {this.props.paymentList.map((payment, i) => (
                          <tr key={`id-${i}`} className="footable-even" style={{}}>
                            <td className="footable-visible footable-first-column">{payment.customerLogin}</td>
                            <td className="footable-visible footable-first-column">
                              {payment.code}
                              <br />
                              {payment.createAt}
                            </td>
                            <td className="footable-visible">
                              <b className="text-danger">
                                {payment.type === PaymentType.ORDER_PAYMENT ? '-' : '+'}
                                {formatCurency(payment.amount)}đ
                              </b>
                            </td>
                            <td className="footable-visible">{formatCurency(payment.newBalance)}đ</td>
                            <td className="footable-visible">{payment.type}</td>
                            <td className="footable-visible">{payment.note}</td>
                            <td className="footable-visible">
                              <span className="label label-success">{payment.status}</span>
                            </td>
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
