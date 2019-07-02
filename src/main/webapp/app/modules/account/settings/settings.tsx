import React from 'react';
import { Button, Col, Alert, Row } from 'reactstrap';
import { connect } from 'react-redux';
import { Translate, translate } from 'react-jhipster';
import { AvForm, AvField } from 'availity-reactstrap-validation';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';

import { locales, languages } from 'app/config/translation';
import { IRootState } from 'app/shared/reducers';
import { getSession } from 'app/shared/reducers/authentication';
import { saveAccountSettings, reset } from './settings.reducer';

import { getOwnerEntity as getUserProfile, updateEntity as updateUserProfile } from 'app/entities/user-profile/user-profile.reducer';

export interface IUserSettingsProps extends StateProps, DispatchProps {}

export interface IUserSettingsState {
  account: any;
}

export class SettingsPage extends React.Component<IUserSettingsProps, IUserSettingsState> {
  componentDidMount() {
    this.props.getUserProfile('');
  }

  componentWillUnmount() {
    this.props.reset();
  }

  handleValidSubmit = (event, values) => {
    const account = {
      ...this.props.account,
      ...values
    };

    this.props.updateUserProfile(account);
    event.persist();
  };

  render() {
    const { userProfileEntity } = this.props;

    return (
      <div>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="user-management" activeSubMenu="setting" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row  border-bottom white-bg dashboard-header">
            <h3>Thông tin tài khoản</h3>
          </div>
          <div className="row wrapper wrapper-content">
            <div className="ibox">
              <div className="ibox-content">
                <AvForm id="settings-form" onValidSubmit={this.handleValidSubmit}>
                  {/* First name */}
                  <AvField
                    className="form-control"
                    name="fullName"
                    label="Họ tên"
                    id="fullName"
                    placeholder="Họ tên"
                    validate={{
                      minLength: { value: 1, errorMessage: translate('settings.messages.validate.firstname.minlength') },
                      maxLength: { value: 100, errorMessage: translate('settings.messages.validate.firstname.maxlength') }
                    }}
                    value={userProfileEntity.fullName}
                  />
                  {/* Email */}
                  <AvField
                    name="email"
                    label={translate('global.form.email')}
                    placeholder={translate('global.form.email.placeholder')}
                    type="email"
                    validate={{
                      minLength: { value: 5, errorMessage: translate('global.messages.validate.email.minlength') },
                      maxLength: { value: 254, errorMessage: translate('global.messages.validate.email.maxlength') }
                    }}
                    value={userProfileEntity.email}
                  />
                  {/* Email */}
                  <AvField
                    className="form-control"
                    name="mobile"
                    label="Mobile"
                    id="mobile"
                    placeholder="Điện thoại"
                    validate={{
                      minLength: { value: 1, errorMessage: translate('settings.messages.validate.firstname.minlength') },
                      maxLength: { value: 11, errorMessage: translate('settings.messages.validate.firstname.maxlength') }
                    }}
                    value={userProfileEntity.mobile}
                  />
                  <Button color="primary" type="submit">
                    Cập nhật
                  </Button>
                </AvForm>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ authentication, userProfile }: IRootState) => ({
  account: authentication.account,
  userProfileEntity: userProfile.entity,
  isAuthenticated: authentication.isAuthenticated
});

const mapDispatchToProps = {
  getSession,
  saveAccountSettings,
  reset,
  getUserProfile,
  updateUserProfile
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SettingsPage);
