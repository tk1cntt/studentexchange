import 'react-toastify/dist/ReactToastify.css';
import './app.scss';

import React from 'react';
import { connect } from 'react-redux';
import { HashRouter as Router } from 'react-router-dom';
import { ToastContainer, ToastPosition, toast } from 'react-toastify';

import { IRootState } from 'app/shared/reducers';
import { getSession } from 'app/shared/reducers/authentication';
import { getProfile } from 'app/shared/reducers/application-profile';
import { setLocale } from 'app/shared/reducers/locale';
import Login from 'app/modules/login/main';
import { hasAnyAuthority } from 'app/shared/auth/private-route';
import { AUTHORITIES } from 'app/config/constants';
import AppRoutes from 'app/routes';

export interface IAppProps extends StateProps, DispatchProps {}

export class App extends React.Component<IAppProps> {
  componentDidMount() {
    this.props.getSession();
  }

  render() {
    const dom =
      this.props.isAuthenticated !== true ? (
        <Login />
      ) : (
        <div>
          <AppRoutes />
        </div>
      );
    return (
      <Router>
        <div id="wrapper">
          <ToastContainer
            position={toast.POSITION.TOP_LEFT as ToastPosition}
            className="toastify-container"
            toastClassName="toastify-toast"
          />
          {dom}
        </div>
      </Router>
    );
  }
}

const mapStateToProps = ({ authentication, locale }: IRootState) => ({
  currentLocale: locale.currentLocale,
  isAuthenticated: authentication.isAuthenticated,
  isAdmin: hasAnyAuthority(authentication.account.authorities, [AUTHORITIES.ADMIN]),
  isManager: hasAnyAuthority(authentication.account.authorities, [AUTHORITIES.MANAGER]),
  isStaff: hasAnyAuthority(authentication.account.authorities, [AUTHORITIES.STAFF])
});

const mapDispatchToProps = { setLocale, getSession, getProfile };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(App);
