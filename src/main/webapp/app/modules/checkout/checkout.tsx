import React from 'react';
import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';

import qs from 'query-string';
import { Cascader } from 'antd';

import { getSession } from 'app/shared/reducers/authentication';
import { getAllEntities as getCities } from 'app/entities/city/city.reducer';
import { getOwnerEntities as getOwnerShippingCart } from 'app/entities/shopping-cart/shopping-cart.reducer';
import {
  createEntity as createShippingAddress,
  getOwnerEntities as getOwnerShippingAddress
} from 'app/entities/user-shipping-address/user-shipping-address.reducer';
import { queryString, stringToSlug } from 'app/shared/util/utils';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';

export interface IHomeProp extends StateProps, DispatchProps {}

export interface ICheckoutProp extends StateProps, DispatchProps {
  location: any;
  history: any;
}

export interface ICheckoutState {
  addressChoose: number;
  city: any;
  parameters: any;
  locations: any;
  addresses: any;
  address: string;
  mobile: string;
  name: string;
  note: string;
}

export class Checkout extends React.Component<ICheckoutProp> {
  state: ICheckoutState = {
    addressChoose: 0,
    address: null,
    name: null,
    mobile: null,
    note: null,
    city: null,
    parameters: {},
    locations: [],
    addresses: []
  };

  componentDidMount() {
    this.props.getOwnerShippingCart();
    this.props.getOwnerShippingAddress();
    if (this.props.cities.length === 0) {
      this.props.getCities();
    } else {
      this.mappingCity();
    }
    /*
    if (this.props.location) {
      const parsed = qs.parse(this.props.location.search);
      if (parsed) {
        // tslint:disable-next-line 
        const city = parsed.cityId ? [parseInt(parsed.cityId), parseInt(parsed.districtId), parseInt(parsed.wardId)] : null;
        this.setState({
          parameters: parsed,
          city
        });
      }
    }
    //*/
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

  decreaseQuantity = (item: any) => {
    // console.log(item);
  };

  onChange = e => {
    this.setState({
      addressChoose: e.target.value
    });
  };

  onChangeAddress = e => {
    this.setState({
      address: e.target.value
    });
  };

  onChangeMobile = e => {
    this.setState({
      mobile: e.target.value
    });
  };

  onChangeNote = e => {
    this.setState({
      note: e.target.value
    });
  };

  onChangeName = e => {
    this.setState({
      name: e.target.value
    });
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

  createAddressClick = () => {
    const address = {
      cityId: this.state.parameters.cityId,
      districtId: this.state.parameters.districtId,
      name: this.state.name,
      address: this.state.address,
      mobile: this.state.mobile,
      note: this.state.note
    };
    this.props.createShippingAddress(address);
    this.setState({
      addressChoose: 0
    });
  };

  userShippingAddressListBox() {
    return (
      <div className="ibox-content">
        <div className="form-group" id="toastTypeGroup">
          <label>Chọn địa chỉ nhận hàng</label>
          {this.props.userShippingAddressList.map((userShippingAddress, ii) => (
            <div className="radio" key={`entity-${ii}`}>
              <label>
                <input type="radio" name="toasts" value="123" onChange={this.onChange} defaultChecked />
                <b>{userShippingAddress.name}</b> - {userShippingAddress.mobile}
                <br />
                {userShippingAddress.address}
                <br />
                <div className="shipping-note">{userShippingAddress.note}</div>
              </label>
            </div>
          ))}
          <div className="radio">
            <label>
              <input type="radio" name="toasts" value="123" onChange={this.onChange} defaultChecked />
              <b>Lê Thị Quỳnh Trang</b> - 0973556590
              <br />
              Số 165 Bạch Đằng, Ngân Hàng Á Châu (ACB) - Thành Phố Hải Dương - Hải Dương
              <br />
              <div className="shipping-note">Trong giờ hành chính</div>
            </label>
          </div>
          <div className="radio">
            <label className="radio">
              <input type="radio" name="toasts" value="456" onChange={this.onChange} />
              <b>Nguyen Thanh Cong</b> - 0973556590
              <br />
              So 1 Ngo 2 - Quận Cầu Giấy - Hà Nội
            </label>
          </div>
          <div className="radio">
            <label className="radio">
              <input type="radio" name="toasts" value="789" onChange={this.onChange} />
              Thêm địa chỉ nhận hàng
            </label>
          </div>
        </div>
        {this.state.addressChoose == 789 ? this.addressBox() : ''}
      </div>
    );
  }

  addressBox() {
    return (
      <form role="form" id="form">
        <div className="form-group">
          <label>Người nhận</label>{' '}
          <input
            type="text"
            placeholder="Tên người nhận"
            className="form-control"
            required
            aria-required="true"
            onChange={this.onChangeName}
          />
        </div>
        <div className="form-group">
          <label>Thành phố</label>
          <Cascader value={this.state.city} options={this.state.locations} onChange={this.onChangeCascader} placeholder="Chọn thành phố" />
        </div>
        <div className="form-group">
          <label>Địa chỉ người nhận</label>
          <input type="text" placeholder="Địa chỉ" className="form-control" name="address" onChange={this.onChangeAddress} />
        </div>
        <div className="form-group">
          <label>Số điên thoại</label>
          <input type="text" placeholder="Số điện thoại" className="form-control" name="mobile" onChange={this.onChangeMobile} />
        </div>
        <div className="form-group">
          <label>Ghi chú</label>
          <input type="text" placeholder="Enter email" className="form-control" name="note" onChange={this.onChangeNote} />
        </div>
        <div>
          <button className="btn btn-sm btn-primary m-t-n-xs" type="button" onClick={this.createAddressClick}>
            <strong>Thêm địa chỉ</strong>
          </button>
        </div>
      </form>
    );
  }

  render() {
    const { shoppingCartList, account } = this.props;
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="shopping-cart" activeSubMenu="" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row  border-bottom white-bg dashboard-header">
            Noi dung phia tren
            <button className="btn btn-primary btn-block m-t checkout-cart">
              <Link to={'/checkout'}>
                <i className="fa fa-shopping-cart" /> Đặt tất cả hàng
              </Link>
            </button>
          </div>
          <div className="row wrapper wrapper-content">
            <div className="shipping-box">
              <div className="ibox-content">
                <div className="form-group" id="toastTypeGroup">
                  <label>Chọn địa chỉ nhận hàng</label>
                  <div className="radio">
                    <label>
                      <input type="radio" name="toasts" value="123" onChange={this.onChange} defaultChecked />
                      <b>Lê Thị Quỳnh Trang</b> - 0973556590
                      <br />
                      Số 165 Bạch Đằng, Ngân Hàng Á Châu (ACB) - Thành Phố Hải Dương - Hải Dương
                      <br />
                      <div className="shipping-note">Trong giờ hành chính</div>
                    </label>
                  </div>
                  <div className="radio">
                    <label className="radio">
                      <input type="radio" name="toasts" value="456" onChange={this.onChange} />
                      <b>Nguyen Thanh Cong</b> - 0973556590
                      <br />
                      So 1 Ngo 2 - Quận Cầu Giấy - Hà Nội
                    </label>
                  </div>
                  <div className="radio">
                    <label className="radio">
                      <input type="radio" name="toasts" value="789" onChange={this.onChange} />
                      Thêm địa chỉ nhận hàng
                    </label>
                  </div>
                </div>
                {this.state.addressChoose == 789 ? this.addressBox() : ''}
              </div>
            </div>
            {shoppingCartList.map((shoppingCart, ii) => (
              <div key={`entity-${ii}`}>
                <div className="col-xs-12 col-md-12">
                  <div className="row">
                    <div className="ibox float-e-margins">
                      <div className="ibox-title">
                        <h5>{`${shoppingCart.aliwangwang}`}</h5>
                        <div className="ibox-tools">
                          <span className="label label-warning-light pull-right">{`${shoppingCart.items.length}`} mặt hàng trong giỏ</span>
                        </div>
                      </div>
                      <div className="ibox-content">
                        <div>
                          <div className="feed-activity-list">
                            {shoppingCart.items.map((item, iy) => (
                              <div className="feed-element" key={`entity-${iy}`}>
                                <a href="profile.html" className="pull-left">
                                  <img alt="image" className="img-circle" src={`${item.propertiesImage}`} />
                                </a>
                                <div className="media-body ">
                                  <small className="pull-right">
                                    <div className="input-group bootstrap-touchspin">
                                      <span className="input-group-btn">
                                        <button className="btn btn-default bootstrap-touchspin-down" type="button">
                                          -
                                        </button>
                                      </span>
                                      <input type="tel" className="form-control quantity" min="0" defaultValue={`${item.quantity}`} />
                                      <span className="input-group-btn">
                                        <button className="btn btn-default bootstrap-touchspin-up" type="button">
                                          +
                                        </button>
                                      </span>
                                    </div>
                                  </small>
                                  <strong>{`${item.itemName}`}</strong>
                                  <br />
                                  <small className="text-muted">
                                    Thuộc tính: {`${item.propertiesName}`}({`${item.propertiesType}`})<br />
                                    Số lượng: {`${item.quantity}`}
                                    <br />
                                    Đơn giá: ¥{`${item.itemPriceNDT}`}
                                  </small>
                                </div>
                              </div>
                            ))}
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col-xs-12">
                      <div className="row checkout-cart-detail">
                        <button className="btn btn-primary btn-block">
                          <span className="checkout-cart">
                            <Link to={`/checkout?shopid=${ii}-12345`}>
                              <i className="fa fa-shopping-cart" /> Đặt hàng
                            </Link>
                          </span>
                        </button>
                        <div className="col-xs-8 item">Tiền hàng:</div>
                        <div className="col-xs-4 item">
                          <b>200,000,000đ</b>
                        </div>
                        <div className="col-xs-8 item">Phí mua hàng:</div>
                        <div className="col-xs-4 item">
                          <b>2,000,000đ</b>
                        </div>
                        <div className="col-xs-8 item">Phí kiểm đếm:</div>
                        <div className="col-xs-4 item">
                          <b>200,000,000đ</b>
                        </div>
                        <div className="col-xs-8 item">Phí vận chuyển nội địa TQ:</div>
                        <div className="col-xs-4 item">
                          <b>200,000,000đ</b>
                        </div>
                        <div className="col-xs-8 item">Phí đóng kiện gỗ:</div>
                        <div className="col-xs-4 item">
                          <b>200,000,000đ</b>
                        </div>
                        <div className="col-xs-8 item">Phí vận chuyển TQ - VN:</div>
                        <div className="col-xs-4 item">
                          <b>200,000,000đ</b>
                        </div>
                        <div className="col-xs-8 item">Phí vận chuyển nội địa VN:</div>
                        <div className="col-xs-4 item">
                          <b>200,000,000đ</b>
                        </div>
                      </div>
                      <div className="row checkout-cart-detail checkout-cart-total">
                        <div className="col-xs-8 item">
                          <h4>Tổng tiền:</h4>
                        </div>
                        <div className="col-xs-4 item">
                          <b>200,000,000đ</b>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>
          <div className="footer">
            <div className="pull-right">
              10GB of <strong>250GB</strong> Free.
            </div>
            <div>
              <strong>Copyright</strong> Example Company © 2014-2017
            </div>
          </div>
        </div>
      </>
    );
  }
}

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  shoppingCartList: storeState.shoppingCart.entities,
  isAuthenticated: storeState.authentication.isAuthenticated,
  cities: storeState.city.entities,
  userShippingAddressList: storeState.userShippingAddress.entities
});

const mapDispatchToProps = {
  getSession,
  getCities,
  createShippingAddress,
  getOwnerShippingAddress,
  getOwnerShippingCart
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Checkout);
