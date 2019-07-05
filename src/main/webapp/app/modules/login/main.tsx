/* tslint:disable */
import React from 'react';
import { connect } from 'react-redux';
import { Translate, translate } from 'react-jhipster';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Label, Alert, Row, Col } from 'reactstrap';
import { AvForm, AvField, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { Link } from 'react-router-dom';
import AccountKit from 'react-facebook-account-kit';

import { IRootState } from 'app/shared/reducers';
import { login, fblogin } from 'app/shared/reducers/authentication';

export interface ILoginProps {
  isAuthenticated: boolean;
  loginError: boolean;
  login: Function;
  fblogin: Function;
}

export class Login extends React.Component<ILoginProps> {
  handleSubmit = (event, errors, { username, password, rememberMe }) => {
    this.props.login(username, password, rememberMe);
  };

  // login callback
  loginCallback = response => {
    if (response.status === 'PARTIALLY_AUTHENTICATED') {
      this.props.fblogin(response.code, true);
    } else if (response.status === 'NOT_AUTHENTICATED') {
      // handle authentication failure
    } else if (response.status === 'BAD_PARAMS') {
      // handle bad parameters
    }
  };

  render() {
    const { isAuthenticated } = this.props;
    const csrf = Math.random()
      .toString(36)
      .replace(/[^a-z]+/g, '');
    return (
      <div className="middle-box text-center loginscreen animated fadeInDown">
        <div>
          <div>
            <h1 className="logo-name">IN+</h1>
          </div>
          <AvForm onSubmit={this.handleSubmit}>
            <Row>
              <Col md="12">
                {this.props.loginError ? (
                  <Alert color="danger">
                    <Translate contentKey="login.messages.error.authentication">
                      <strong>Failed to sign in!</strong> Please check your credentials and try again.
                    </Translate>
                  </Alert>
                ) : null}
              </Col>
              <Col md="12">
                <AvField
                  name="username"
                  label={translate('global.form.username')}
                  placeholder={translate('global.form.username.placeholder')}
                  required
                  errorMessage="Username cannot be empty!"
                  autoFocus
                />
                <AvField
                  name="password"
                  type="password"
                  label={translate('login.form.password')}
                  placeholder={translate('login.form.password.placeholder')}
                  required
                  errorMessage="Password cannot be empty!"
                />
                <AvGroup check inline>
                  <Label className="form-check-label">
                    <AvInput type="checkbox" name="rememberMe" /> <Translate contentKey="login.form.rememberme">Remember me</Translate>
                  </Label>
                </AvGroup>
              </Col>
            </Row>
            <button className="btn btn-primary block full-width m-b" type="submit">
              Đăng nhập
            </button>
          </AvForm>
          <div>
            <AccountKit
              appId="504517876723313"
              version="v1.1"
              onResponse={this.loginCallback}
              csrf={`${csrf}`} // Required for security
              countryCode={'+84'} // eg. +60
              language={'vi_VN'}
            >
              {p => (
                <button {...p} className="btn btn-sm btn-white btn-block">
                  Đăng nhập sử dụng điện thoại
                </button>
              )}
            </AccountKit>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ authentication }: IRootState) => ({
  isAuthenticated: authentication.isAuthenticated,
  loginError: authentication.loginError
});

const mapDispatchToProps = { login, fblogin };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Login);
