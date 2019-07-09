import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import OrderDeposited from './order/order-deposited';
import OrderBuying from '../staff/order/order-buying';
import OrderPurchased from './order/order-purchased';
import Buying from '../staff/buying/buying';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/order-deposited`} component={OrderDeposited} />
      <ErrorBoundaryRoute path={`${match.url}/order-buying`} component={OrderBuying} />
      <ErrorBoundaryRoute path={`${match.url}/order-purchased`} component={OrderPurchased} />
      <ErrorBoundaryRoute path={`${match.url}/buying`} component={Buying} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
