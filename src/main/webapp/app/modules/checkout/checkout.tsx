import React from 'react';

import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';

import qs from 'query-string';
import { Cascader } from 'antd';

import { getSession } from 'app/shared/reducers/authentication';
import { getAllEntities as getCities } from 'app/entities/city/city.reducer';
import {
  getOwnerEntities as getOwnerShippingCart,
  getOwnerEntity as getShippingCart
} from 'app/entities/shopping-cart/shopping-cart.reducer';
import {
  createEntity as createShippingAddress,
  getOwnerEntities as getOwnerShippingAddress
} from 'app/entities/user-shipping-address/user-shipping-address.reducer';
import { formatCurency, stringToSlug, encodeId, decodeId } from 'app/shared/util/utils';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';
import Footer from 'app/shared/layout/footer/footer';

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
  isCreateAddress: boolean;
  totalQuantity: number;
  totalAmount: number;
}

export class Checkout extends React.Component<ICheckoutProp> {
  state: ICheckoutState = {
    isCreateAddress: false,
    addressChoose: -1,
    address: null,
    name: null,
    mobile: null,
    note: null,
    city: null,
    parameters: {},
    locations: [],
    addresses: [],
    totalQuantity: 0,
    totalAmount: 0
  };

  componentDidMount() {
    this.props.getOwnerShippingAddress();
    if (this.props.cities.length === 0) {
      this.props.getCities();
    } else {
      this.mappingCity();
    }
    if (this.props.location) {
      const parsed = qs.parse(this.props.location.search);
      if (parsed.shopid) {
        this.props.getShippingCart(decodeId(parsed.shopid));
      } else {
        this.props.getOwnerShippingCart();
      }
    }
  }

  componentDidUpdate(prevProps) {
    // Typical usage (don't forget to compare props):
    if (this.props.cities !== prevProps.cities) {
      this.mappingCity();
    }
    if (this.props.shoppingCartList !== prevProps.shoppingCartList) {
      this.checkoutAmountData();
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

  onChangeShippingAddress = e => {
    // tslint:disable-next-line
    if (e.target.value == 0) {
      this.setState({
        addressChoose: e.target.value,
        isCreateAddress: true
      });
    } else {
      this.setState({
        addressChoose: e.target.value,
        isCreateAddress: false
      });
    }
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

  createUserShippingAddressClick = () => {
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
      addressChoose: -1,
      isCreateAddress: false
    });
    window.scrollTo(0, 0);
  };

  userShippingAddressListBox() {
    return (
      <div className="ibox-content">
        <div className="form-group" id="toastTypeGroup">
          <div>
            <label>Chọn địa chỉ nhận hàng</label>
            {'    '}
            <Link to="/entity/user-shipping-address">
              <i className="fa fa-edit" /> Sửa địa chỉ
            </Link>
          </div>
          {this.props.userShippingAddressList.map((userShippingAddress, ii) => (
            <div className="radio" key={`entity-${ii}`}>
              <label>
                <input
                  type="radio"
                  name="userShippingAddressId"
                  value={`${userShippingAddress.id}`}
                  onChange={this.onChangeShippingAddress}
                />
                <b>{userShippingAddress.name}</b> - {userShippingAddress.mobile}
                <br />
                {userShippingAddress.address}
                <br />
                <div className="shipping-note">{userShippingAddress.note}</div>
              </label>
            </div>
          ))}
          <div className="radio">
            <label className="radio">
              <input
                type="radio"
                name="userShippingAddressId"
                value="0"
                onChange={this.onChangeShippingAddress}
                checked={this.state.isCreateAddress}
              />
              Thêm địa chỉ nhận hàng
            </label>
          </div>
        </div>
        {// tslint:disable-next-line
        this.state.addressChoose == 0 ? this.createUserShippingAddressBox() : ''}
      </div>
    );
  }

  createUserShippingAddressBox() {
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
          <input type="text" placeholder="Trong giờ hành chính" className="form-control" name="note" onChange={this.onChangeNote} />
        </div>
        <div>
          <button className="btn btn-sm btn-primary m-t-n-xs" type="button" onClick={this.createUserShippingAddressClick}>
            <strong>Thêm địa chỉ</strong>
          </button>
        </div>
      </form>
    );
  }

  checkoutBox() {
    const { shoppingCartList } = this.props;
    const cartItem = [];
    shoppingCartList.map((shoppingCart, i) => {
      cartItem.push(
        <tr key={`key-${i}`} className="footable-even" style={{}}>
          <td className="footable-visible">
            <a href={`${shoppingCart.shopLink}`} target="_blank">
              {shoppingCart.shopName}
            </a>
            <br />
            {shoppingCart.website}
          </td>
          <td className="footable-visible">{shoppingCart.totalQuantity}</td>
          <td className="footable-visible">{formatCurency(Math.ceil(shoppingCart.totalAmount * this.props.currencyRateEntity.rate))}đ</td>
        </tr>
      );
    });
    return cartItem;
  }

  checkoutAmountData() {
    const { shoppingCartList } = this.props;
    let totalQuantity = 0;
    let totalAmount = 0;
    shoppingCartList.map(shoppingCart => {
      totalQuantity = totalQuantity + shoppingCart.totalQuantity;
      totalAmount = totalAmount + shoppingCart.totalAmount;
    });
    this.setState({
      totalQuantity,
      totalAmount
    });
  }

  render() {
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
            <div className="shipping-box">{this.userShippingAddressListBox()}</div>
            <div className="ibox">
              <div className="ibox-content">
                <table className="footable table table-stripped toggle-arrow-tiny tablet breakpoint footable-loaded">
                  <thead>
                    <tr>
                      <th data-hide="phone" className="footable-visible footable-sortable">
                        Shop
                        <span className="footable-sort-indicator" />
                      </th>
                      <th data-hide="phone" className="footable-visible footable-sortable">
                        Số lượng
                        <span className="footable-sort-indicator" />
                      </th>
                      <th data-hide="phone" className="footable-visible footable-sortable">
                        Tổng tiền
                        <span className="footable-sort-indicator" />
                      </th>
                    </tr>
                  </thead>
                  <tbody>{this.checkoutBox()}</tbody>
                </table>
              </div>
            </div>
            <div className="col-xs-12">
              <div className="row checkout-cart-detail">
                <button className="btn btn-primary btn-block">
                  <span className="checkout-cart">
                    <Link to={`/checkout?shopid=12345`}>
                      <i className="fa fa-shopping-cart" /> Đặt hàng
                    </Link>
                  </span>
                </button>
                <div className="col-xs-8 item">Tiền hàng:</div>
                <div className="col-xs-4 item">
                  <b>{formatCurency(Math.ceil(this.state.totalAmount * this.props.currencyRateEntity.rate))}đ</b>
                </div>
                <div className="col-xs-8 item">Phí mua hàng:</div>
                <div className="col-xs-4 item">
                  <b>{formatCurency(Math.ceil(this.state.totalAmount * this.props.currencyRateEntity.rate * 0.02))}đ</b>
                </div>
                <div className="col-xs-8 item">Phí kiểm đếm:</div>
                <div className="col-xs-4 item">
                  <b>{formatCurency(this.state.totalQuantity * 5000)}đ</b>
                </div>
                <div className="col-xs-8 item">Phí vận chuyển nội địa TQ:</div>
                <div className="col-xs-4 item">
                  <b>0đ</b>
                </div>
                <div className="col-xs-8 item">Phí đóng kiện gỗ:</div>
                <div className="col-xs-4 item">
                  <b>0đ</b>
                </div>
                <div className="col-xs-8 item">Phí vận chuyển TQ - VN:</div>
                <div className="col-xs-4 item">
                  <b>0đ</b>
                </div>
                <div className="col-xs-8 item">Phí vận chuyển nội địa VN:</div>
                <div className="col-xs-4 item">
                  <b>0đ</b>
                </div>
              </div>
              <div className="row checkout-cart-detail checkout-cart-total">
                <div className="col-xs-8 item">
                  <h4>Tổng tiền:</h4>
                </div>
                <div className="col-xs-4 item">
                  <b>{formatCurency(Math.ceil(this.state.totalAmount * this.props.currencyRateEntity.rate * 1.02))}đ</b>
                </div>
              </div>
            </div>
          </div>
          <Footer />
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
  userShippingAddressList: storeState.userShippingAddress.entities,
  currencyRateEntity: storeState.currencyRate.entity
});

const mapDispatchToProps = {
  getSession,
  getCities,
  createShippingAddress,
  getOwnerShippingAddress,
  getOwnerShippingCart,
  getShippingCart
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Checkout);
