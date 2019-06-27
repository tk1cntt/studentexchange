import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import UserShippingAddress from './user-shipping-address';
import UserShippingAddressUpdate from './user-shipping-address-update';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={UserShippingAddressUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={UserShippingAddressUpdate} />
      <ErrorBoundaryRoute path={match.url} component={UserShippingAddress} />
    </Switch>
  </>
);

export default Routes;
