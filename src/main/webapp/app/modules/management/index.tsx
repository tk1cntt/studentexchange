import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BankTransfer from './banktransfer/banktransfer';
import History from './history/history';
import OrderPending from './order/order-pending';
import OrderDeposit from './order/order-deposit';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/banktransfer`} component={BankTransfer} />
      <ErrorBoundaryRoute path={`${match.url}/history`} component={History} />
      <ErrorBoundaryRoute path={`${match.url}/order-pending`} component={OrderPending} />
      <ErrorBoundaryRoute path={`${match.url}/order-deposit`} component={OrderDeposit} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
