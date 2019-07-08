import React from 'react';

import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';

import qs from 'query-string';
import { Cascader, Modal, Button } from 'antd';

import { getSession } from 'app/shared/reducers/authentication';
import { getAllEntities as getCities } from 'app/entities/city/city.reducer';
import {
  getOwnerEntities as getOwnerShippingCart,
  getOwnerEntity as getShippingCart,
  reset
} from 'app/entities/shopping-cart/shopping-cart.reducer';
import {
  createEntity as createShippingAddress,
  getOwnerEntities as getOwnerShippingAddress
} from 'app/entities/user-shipping-address/user-shipping-address.reducer';
import { createEntity as createOrder } from 'app/entities/order-cart/order-cart.reducer';
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
  userShippingAddressChoose: number;
  userShippingAddress: any;
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
  serviceFee: number;
  tallyFee: number;
  finalAmount: number;
  shopid: string;
}

export class Checkout extends React.Component<ICheckoutProp> {
  state: ICheckoutState = {
    isCreateAddress: false,
    userShippingAddressChoose: -1,
    userShippingAddress: null,
    address: null,
    name: null,
    mobile: null,
    note: null,
    city: null,
    parameters: {},
    locations: [],
    addresses: [],
    totalQuantity: 0,
    totalAmount: 0,
    serviceFee: 0,
    tallyFee: 0,
    finalAmount: 0,
    shopid: null
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
        this.setState({
          shopid: decodeId(parsed.shopid)
        });
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

  componentWillUnmount() {
    this.props.reset();
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
        userShippingAddressChoose: e.target.value,
        isCreateAddress: true
      });
    } else {
      this.setState({
        userShippingAddressChoose: e.target.value,
        isCreateAddress: false
      });
      this.props.userShippingAddressList.map(shippingAddress => {
        if (Number(shippingAddress.id) === Number(e.target.value)) {
          this.setState({
            userShippingAddress: shippingAddress
          });
        }
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
      userShippingAddressChoose: -1,
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
                {userShippingAddress.address} - {userShippingAddress.districtType} {userShippingAddress.districtName} -{' '}
                {userShippingAddress.cityName}
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
        {Number(this.state.userShippingAddressChoose) === 0 ? this.createUserShippingAddressBox() : ''}
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
          <td className="footable-visible">{formatCurency(shoppingCart.totalAmount)}đ</td>
        </tr>
      );
    });
    return cartItem;
  }

  checkoutAmountData() {
    const { shoppingCartList } = this.props;
    let totalQuantity = 0;
    let totalAmount = 0;
    let serviceFee = 0;
    let tallyFee = 0;
    let finalAmount = 0;
    shoppingCartList.map(shoppingCart => {
      totalQuantity = totalQuantity + shoppingCart.totalQuantity;
      totalAmount = totalAmount + shoppingCart.totalAmount;
      serviceFee = serviceFee + shoppingCart.serviceFee ? shoppingCart.serviceFee : 0;
      tallyFee = tallyFee + shoppingCart.tallyFee ? shoppingCart.tallyFee : 0;
    });
    finalAmount = totalAmount + serviceFee + tallyFee;
    this.setState({
      totalQuantity,
      totalAmount,
      serviceFee,
      tallyFee,
      finalAmount
    });
  }

  orderItem = () => {
    if (this.state.userShippingAddressChoose < 1) {
      Modal.error({
        title: 'Cảnh báo',
        content: 'Hãy lựa chọn địa chỉ giao hàng'
      });
    } else {
      this.props.createOrder({ userShippingAddressId: encodeId(this.state.userShippingAddressChoose) });
      this.props.history.push('/order-cart');
    }
  };

  checkoutForm() {
    const checkout = (
      <button className="btn btn-danger btn-block" onClick={this.orderItem}>
        <span className="checkout-cart">
          <i className="fa fa-shopping-cart" /> Đặt hàng
        </span>
      </button>
    );
    const payment = (
      <Link to={`/payment`}>
        <button className="btn btn-danger btn-block">
          <span className="checkout-cart">
            <i className="fa fa-shopping-cart" /> Nạp tiền
          </span>
        </button>
      </Link>
    );
    if (this.props.userBalanceEntity.balanceAvailable < this.state.finalAmount * 0.7) {
      return (
        <>
          <div className="col-xs-6 item">
            Quý khách đang thiếu:{' '}
            <b className="text-danger">
              {formatCurency(Math.ceil(this.state.finalAmount * 0.7) - this.props.userBalanceEntity.balanceAvailable)}đ
            </b>
          </div>
          <div className="col-xs-6 item" />
          <div className="col-xs-6 item">Quý khách vui lòng nạp tiền để đặt cọc</div>
          {payment}
        </>
      );
    } else {
      return (
        <>
          <div className="col-xs-6 item">Quý khách đã có đủ số tiền cọc. Hãy tiến hành đặt hàng</div>
          {checkout}
        </>
      );
    }
  }

  render() {
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="shopping-cart" activeSubMenu="" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row wrapper wrapper-content">
            <div className="row border-bottom white-bg dashboard-header">
              <h4>Tiến hành đặt hàng</h4>
            </div>
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
                <div className="col-xs-6 item">
                  Tổng tiền: <b>{formatCurency(this.state.finalAmount)}đ</b>
                </div>
                <div className="col-xs-6 item">
                  Số dư hiện tại: <b>{formatCurency(this.props.userBalanceEntity.balanceAvailable)}đ</b>
                </div>
                <div className="col-xs-6 item">
                  Đặt cọc (70%): <b>{formatCurency(Math.ceil(this.state.finalAmount * 0.7))}đ</b>
                </div>
                {this.checkoutForm()}
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
  currencyRateEntity: storeState.currencyRate.entity,
  userBalanceEntity: storeState.userBalance.entity
});

const mapDispatchToProps = {
  getSession,
  getCities,
  createShippingAddress,
  getOwnerShippingAddress,
  getOwnerShippingCart,
  getShippingCart,
  createOrder,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Checkout);
