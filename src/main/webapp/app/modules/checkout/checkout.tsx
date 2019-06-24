import React from 'react';
import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';

import qs from 'query-string';
import { Cascader } from 'antd';

import { getSession } from 'app/shared/reducers/authentication';
import { getOwnerEntities } from 'app/entities/shopping-cart/shopping-cart.reducer';
import { getAllEntities as getCities } from 'app/entities/city/city.reducer';
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
}

export class Checkout extends React.Component<ICheckoutProp> {
  state: ICheckoutState = {
    addressChoose: 0,
    city: null,
    parameters: {},
    locations: [],
    addresses: []
  };

  componentDidMount() {
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
        /*
        district.wards.map(ward => {
          const wardData = {
            value: ward.id,
            label: ward.type + ' ' + ward.name
          };
          const address = {
            value: stringToSlug(ward.type + ward.name + district.type + district.name + city.name),
            label: ward.type + ' ' + ward.name + ' - ' + district.type + ' ' + district.name + ' - ' + city.name,
            id: ward.id
          };
          addresses.push(address);
          districtData.children.push(wardData);
        });
        */
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
    console.log('radio checked', e.target.value);
    this.setState({
      addressChoose: e.target.value
    });
  };

  onChangeCascader = value => {
    const parameters = {
      cityId: value[0],
      districtId: value[1],
      wardId: value[2]
    };
    const nextParameter = { ...this.state.parameters, ...parameters };
    this.setState({
      parameters: nextParameter,
      city: value
    });
  };

  addressBox() {
    return (
      <form role="form" id="form">
        <div className="form-group">
          <label>Tên người nhận</label>{' '}
          <input type="text" placeholder="Tên người nhận" className="form-control" required aria-required="true" />
        </div>
        <div className="form-group">
          <label>Thành phố</label>
          <Cascader value={this.state.city} options={this.state.locations} onChange={this.onChangeCascader} placeholder="Chọn thành phố" />
        </div>
        <div className="form-group">
          <label>Địa chỉ người nhận</label> <input type="text" placeholder="Địa chỉ" className="form-control" name="url" />
        </div>
        <div className="form-group">
          <label>Số điên thoại</label> <input type="text" placeholder="Số điện thoại" className="form-control" name="number" />
        </div>
        <div className="form-group">
          <label>MinLength</label> <input type="text" placeholder="Enter email" className="form-control" name="min" />
        </div>
        <div className="form-group">
          <label>MaxLength</label> <input type="text" placeholder="Enter email" className="form-control" name="max" />
        </div>
        <div>
          <button className="btn btn-sm btn-primary m-t-n-xs" type="submit">
            <strong>Thêm địa chỉ</strong>
          </button>
        </div>
      </form>
    );
  }

  render() {
    const radioStyle = {
      display: 'block',
      height: '30px',
      lineHeight: '30px'
    };
    const { shoppingCartList, account } = this.props;
    console.log('this.state.value', this.state.addressChoose, this.state.addressChoose === 789);
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
  cities: storeState.city.entities
});

const mapDispatchToProps = { getSession, getOwnerEntities, getCities };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Checkout);