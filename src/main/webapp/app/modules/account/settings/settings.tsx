import React from 'react';
import { Button, Col, Alert, Row } from 'reactstrap';
import { connect } from 'react-redux';
import { Translate, translate } from 'react-jhipster';
import { AvForm, AvField } from 'availity-reactstrap-validation';

import { Cascader } from 'antd';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';

import { IRootState } from 'app/shared/reducers';
import { getSession } from 'app/shared/reducers/authentication';
import { saveAccountSettings, reset } from './settings.reducer';
import { stringToSlug } from 'app/shared/util/utils';

import { getAllEntities as getCities } from 'app/entities/city/city.reducer';
import { getOwnerEntity as getUserProfile, updateEntity as updateUserProfile } from 'app/entities/user-profile/user-profile.reducer';

export interface IUserSettingsProps extends StateProps, DispatchProps {}

export interface IUserSettingsState {
  account: any;
  locations: any;
  addresses: any;
  parameters: any;
  city: any;
  address: string;
}

export class SettingsPage extends React.Component<IUserSettingsProps, IUserSettingsState> {
  state: IUserSettingsState = {
    account: null,
    address: null,
    city: null,
    parameters: {},
    locations: [],
    addresses: []
  };

  componentDidMount() {
    this.props.getUserProfile('');
    if (this.props.cities.length === 0) {
      this.props.getCities();
    } else {
      this.mappingCity();
    }
  }

  componentDidUpdate(prevProps) {
    // Typical usage (don't forget to compare props):
    if (this.props.cities !== prevProps.cities) {
      this.mappingCity();
    }
  }

  mappingCity() {
    const locations = []; // this.state.locations;
    const addresses = [];
    this.props.cities.map(city => {
      const cityData = {
        value: city.id,
        label: city.name,
        children: []
      };
      city.districts.map(district => {
        const districtData = {
          value: district.id,
          label: district.type + ' ' + district.name,
          children: []
        };
        const address = {
          value: stringToSlug(district.type + district.name + city.name),
          label: district.type + ' ' + district.name + ' - ' + city.name,
          cityId: city.id,
          districtId: district.id,
          id: district.id
        };
        addresses.push(address);
        cityData.children.push(districtData);
      });
      locations.push(cityData);
    });
    this.setState({
      locations,
      addresses
    });
  }

  componentWillUnmount() {
    this.props.reset();
  }

  handleValidSubmit = (event, values) => {
    const account = {
      ...this.props.userProfileEntity,
      ...values,
      cityId: this.state.parameters.cityId ? this.state.parameters.cityId : this.props.userProfileEntity.cityId,
      districtId: this.state.parameters.districtId ? this.state.parameters.districtId : this.props.userProfileEntity.districtId,
      address: this.state.address
    };

    this.props.updateUserProfile(account);
    event.persist();
  };

  onChangeCascader = value => {
    const parameters = {
      cityId: value[0],
      districtId: value[1]
    };
    const nextParameter = { ...this.state.parameters, ...parameters };
    this.setState({
      parameters: nextParameter,
      city: value
    });
  };

  onChangeAddress = e => {
    this.setState({
      address: e.target.value
    });
  };

  render() {
    const { userProfileEntity } = this.props;
    const defaultCascaderValue = [this.props.userProfileEntity.cityId, this.props.userProfileEntity.districtId];

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
                  <div className="form-group">
                    <label>Số điện thoại</label>
                    <input
                      type="text"
                      disabled
                      placeholder="Số điện thoại"
                      className="form-control"
                      name="mobile"
                      defaultValue={this.props.userProfileEntity.mobile}
                    />
                  </div>
                  {/* First name */}
                  <AvField
                    className="form-control"
                    name="fullName"
                    label="Họ tên"
                    id="fullName"
                    placeholder="Họ tên"
                    value={userProfileEntity.fullName}
                  />
                  {/* Email */}
                  <AvField name="email" label="Email" placeholder="Email" type="email" value={userProfileEntity.email} />
                  <div className="form-group">
                    <label>Thành phố</label>
                    <Cascader
                      value={this.state.city || defaultCascaderValue}
                      options={this.state.locations}
                      onChange={this.onChangeCascader}
                      placeholder="Chọn thành phố"
                    />
                  </div>
                  <AvField name="address" label="Địa chỉ" placeholder="Địa chỉ" value={userProfileEntity.address} />
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

const mapStateToProps = ({ authentication, userProfile, city }: IRootState) => ({
  account: authentication.account,
  userProfileEntity: userProfile.entity,
  cities: city.entities,
  isAuthenticated: authentication.isAuthenticated
});

const mapDispatchToProps = {
  getSession,
  saveAccountSettings,
  reset,
  getCities,
  getUserProfile,
  updateUserProfile
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SettingsPage);
