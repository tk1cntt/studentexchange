import React from 'react';

import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';

import { getSession } from 'app/shared/reducers/authentication';
import { getOwnerEntities as getOwnerPayment } from 'app/entities/payment/payment.reducer';
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
    this.props.getOwnerPayment();
  }

  render() {
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="payment" activeSubMenu="" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row  border-bottom white-bg dashboard-header">
            <h4>Quý khách hàng chuyển khoản qua ngân hàng của Abuma dưới đây</h4>
          </div>
          <div className="row wrapper">
            <div className="ibox">
              <div className="ibox-content">
                Số tài khoản: <b className="text-danger">0123456789</b>
                <br />
                Chủ tài khoản: <b>Công ty TNHH Abuma</b>
                <br />
                Ngân hàng: <b>Á Châu (ACB)</b>
                <br />
                Nội dung chuyển khoản ghi rõ:
                <div className="payment-note">
                  <b>Naptien</b> Số điện thoại đăng ký tài khoản
                </div>
                (Ví dụ: Naptien 0973556590)
                <br />
                (*) Với những giao dịch nạp tiền chuyển khoản ngoài giờ hành chính. Thời gian nạp vào hệ thống có thể chậm hơn bình thường.
                Mong quý khách hàng thông cảm!
              </div>
            </div>
          </div>
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
                            <td className="footable-visible footable-first-column">{payment.code}</td>
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

const mapDispatchToProps = { getSession, getOwnerPayment };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Payment);
