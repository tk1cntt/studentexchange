/* tslint:disable */
import React from 'react';
import { connect } from 'react-redux';
import { Translate, translate } from 'react-jhipster';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Label, Alert, Row, Col } from 'reactstrap';
import { AvForm, AvField, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { Link } from 'react-router-dom';

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

  // phone form submission handler
  smsLogin = () => {
    // tslint:disable-next-line
    window.AccountKit.login(
      'PHONE',
      { countryCode: '+84', phoneNumber: '' }, // will use default values if not specified
      this.loginCallback
    );
  };

  // email form submission handler
  emailLogin = () => {
    // tslint:disable-next-line
    window.AccountKit.login('EMAIL', { emailAddress: '' }, this.loginCallback);
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
            <Button className="btn btn-primary block full-width m-b" type="submit">
              Sign in
            </Button>
            <a href="#">
              <small>Forgot password?</small>
            </a>
            <p className="text-muted text-center">
              <small>Do not have an account?</small>
            </p>
            <a className="btn btn-sm btn-white btn-block" href="register.html">
              Create an account
            </a>
          </AvForm>
          <div>
            <button className="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" onClick={this.smsLogin}>
              Login via SMS
            </button>
          </div>
          <div>Or</div>
          <div>
            <button className="mdl-button mdl-js-button mdl-button--raised mdl-button--accent" onClick={this.emailLogin}>
              Login via Email
            </button>
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
