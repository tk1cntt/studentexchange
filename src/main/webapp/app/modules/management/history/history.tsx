import React from 'react';
import { connect } from 'react-redux';

import { getSession } from 'app/shared/reducers/authentication';
import { getEntities as getAllPayment } from 'app/entities/payment/payment.reducer';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';
import Footer from 'app/shared/layout/footer/footer';
import PaymentList from 'app/shared/layout/payment/payment-list-view';

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
              <PaymentList isUser={false} paymentList={this.props.paymentList} />
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
