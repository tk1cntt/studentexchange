import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import UserBalance from './user-balance';
import UserBalanceDetail from './user-balance-detail';
import UserBalanceUpdate from './user-balance-update';
import UserBalanceDeleteDialog from './user-balance-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={UserBalanceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={UserBalanceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={UserBalanceDetail} />
      <ErrorBoundaryRoute path={match.url} component={UserBalance} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={UserBalanceDeleteDialog} />
  </>
);

export default Routes;
