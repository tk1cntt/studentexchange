/* tslint:disable */
import React from 'react';
import { Switch } from 'react-router-dom';
import Loadable from 'react-loadable';

import Login from 'app/modules/login/main';
import Cart from 'app/modules/user/cart/cart';
import Order from 'app/modules/user/order/order';
import Checkout from 'app/modules/user/checkout/checkout';
import Payment from 'app/modules/user/payment/payment';
import Management from 'app/modules/management';
import Logistics from 'app/modules/logistics';
import Staff from 'app/modules/staff';
import Register from 'app/modules/account/register/register';
import Activate from 'app/modules/account/activate/activate';
import PasswordResetInit from 'app/modules/account/password-reset/init/password-reset-init';
import PasswordResetFinish from 'app/modules/account/password-reset/finish/password-reset-finish';
import Logout from 'app/modules/login/logout';
import Home from 'app/modules/home/home';
import Entities from 'app/entities';
import PrivateRoute from 'app/shared/auth/private-route';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import { AUTHORITIES } from 'app/config/constants';

// tslint:disable:space-in-parens
const Account = Loadable({
  loader: () => import(/* webpackChunkName: "account" */ 'app/modules/account'),
  loading: () => <div>loading ...</div>
});

const Admin = Loadable({
  loader: () => import(/* webpackChunkName: "administration" */ 'app/modules/administration'),
  loading: () => <div>loading ...</div>
});
// tslint:enable

const Routes = () => (
  <div className="view-routes">
    <ErrorBoundaryRoute path="/login" component={Login} />
    <Switch>
      <ErrorBoundaryRoute path="/logout" component={Logout} />
      <PrivateRoute path="/admin" component={Admin} hasAnyAuthorities={[AUTHORITIES.ADMIN]} />
      <PrivateRoute path="/account" component={Account} hasAnyAuthorities={[AUTHORITIES.ADMIN, AUTHORITIES.USER]} />
      <PrivateRoute path="/entity" component={Entities} hasAnyAuthorities={[AUTHORITIES.USER]} />
      <PrivateRoute path="/shopping-cart" component={Cart} hasAnyAuthorities={[AUTHORITIES.USER]} />
      <PrivateRoute path="/order-cart" component={Order} hasAnyAuthorities={[AUTHORITIES.USER]} />
      <PrivateRoute path="/checkout" component={Checkout} hasAnyAuthorities={[AUTHORITIES.USER]} />
      <PrivateRoute path="/payment" component={Payment} hasAnyAuthorities={[AUTHORITIES.USER]} />
      <PrivateRoute path="/management" component={Management} hasAnyAuthorities={[AUTHORITIES.MANAGER]} />
      <PrivateRoute path="/logistics" component={Logistics} hasAnyAuthorities={[AUTHORITIES.LOGISTICS]} />
      <PrivateRoute path="/staff" component={Staff} hasAnyAuthorities={[AUTHORITIES.STAFF]} />

      <ErrorBoundaryRoute path="/" component={Home} />
      <ErrorBoundaryRoute path="/register" component={Register} />
      <ErrorBoundaryRoute path="/activate/:key?" component={Activate} />
      <ErrorBoundaryRoute path="/reset/request" component={PasswordResetInit} />
      <ErrorBoundaryRoute path="/reset/finish/:key?" component={PasswordResetFinish} />
    </Switch>
  </div>
);

export default Routes;
