import React from 'react';

import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';

import { getSession } from 'app/shared/reducers/authentication';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';
import Footer from 'app/shared/layout/footer/footer';

export interface IHomeProp extends StateProps, DispatchProps {}

export interface ICheckoutProp extends StateProps, DispatchProps {
  location: any;
  history: any;
}

export interface ICheckoutState {}

export class Checkout extends React.Component<ICheckoutProp> {
  render() {
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="payment" activeSubMenu="" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row  border-bottom white-bg dashboard-header">
            <h4>Quý khách hàng chuyển khoản qua ngân hàng của Abuma dưới đây</h4>
          </div>
          <div className="row wrapper wrapper-content">
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
          <Footer />
        </div>
      </>
    );
  }
}

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  currencyRateEntity: storeState.currencyRate.entity,
  userBalanceEntity: storeState.userBalance.entity
});

const mapDispatchToProps = { getSession };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Checkout);
