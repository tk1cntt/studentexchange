import React from 'react';
import { connect } from 'react-redux';

import { getSession } from 'app/shared/reducers/authentication';
import { getOwnerEntities as getOwnerPayment } from 'app/entities/payment/payment.reducer';
import { AUTHORITIES } from 'app/config/constants';
import { hasAnyAuthority } from 'app/shared/auth/private-route';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';
import Footer from 'app/shared/layout/footer/footer';
import HomeOrderSumary from './home-order-summary-view';
import HomeUserInfo from './home-user-info-view';

export interface IHomeProp extends StateProps, DispatchProps {}

export class Home extends React.Component<IHomeProp> {
  componentDidMount() {
    this.props.getOwnerPayment(0, 5, 'createAt');
  }

  render() {
    return (
      <>
        <Sidebar activeMenu="" activeSubMenu="dashboard" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row border-bottom white-bg dashboard-header">
            <div className="col-md-8">
              <HomeOrderSumary />
            </div>
            <div className="col-md-4">
              <HomeUserInfo isUser={this.props.isUser} userBalanceEntity={this.props.userBalanceEntity} />
            </div>
          </div>
          <div className="row">
            <div className="col-lg-12">
              <div className="wrapper wrapper-content">
                <div className="row">
                  <div className="col-lg-12">
                    <div className="ibox">Chưa có nội dung</div>
                  </div>
                </div>
              </div>
              <div className="footer">
                <Footer />>
              </div>
            </div>
          </div>
        </div>
      </>
    );
  }
}

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated,
  isUser: hasAnyAuthority(storeState.authentication.account.authorities, [AUTHORITIES.USER]),
  userBalanceEntity: storeState.userBalance.entity
});

const mapDispatchToProps = { getSession, getOwnerPayment };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Home);
