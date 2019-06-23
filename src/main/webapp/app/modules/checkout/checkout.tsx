import React from 'react';
import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';

import { Radio } from 'antd';

import { getSession } from 'app/shared/reducers/authentication';
import { getOwnerEntities } from 'app/entities/shopping-cart/shopping-cart.reducer';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';

export interface IHomeProp extends StateProps, DispatchProps {}

export class Checkout extends React.Component<IHomeProp> {
  state = {
    value: 1
  };

  componentDidMount() {
    this.props.getSession();
    this.props.getOwnerEntities();
  }

  decreaseQuantity = (item: any) => {
    // console.log(item);
  };

  onChange = e => {
    console.log('radio checked', e.target.value);
    this.setState({
      value: e.target.value
    });
  };

  render() {
    const radioStyle = {
      display: 'block',
      height: '30px',
      lineHeight: '30px'
    };
    const { shoppingCartList, account } = this.props;
    // console.log('shoppingCartList', shoppingCartList);
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
          <div className="row">
            <div className="col-xs-12 col-md-6">
              <div className="ibox-content">
                <h2>Chọn địa chỉ nhận hàng</h2>
                <p>
                  <Radio.Group onChange={this.onChange} value={this.state.value}>
                    <Radio style={radioStyle} value={1}>
                      Option A
                    </Radio>
                    <Radio style={radioStyle} value={2}>
                      Option B
                    </Radio>
                    <Radio style={radioStyle} value={3}>
                      Option C
                    </Radio>
                  </Radio.Group>
                  <div className="form-group" id="toastTypeGroup">
                    <label>Toast Type</label>
                    <div className="radio">
                      <label>
                        <input type="radio" name="toasts" />
                        <b>Lê Thị Quỳnh Trang</b> - 0973556590
                        <br />
                        số 165 Bạch Đằng, Ngân Hàng Á Châu (ACB) - Thành Phố Hải Dương - Hải Dương
                        <br />
                        <div className="shipping-note">Trong giờ hành chính</div>
                      </label>
                    </div>
                    <div className="radio">
                      <label className="radio">
                        <input type="radio" name="toasts" />
                        <b>Nguyen Thanh Cong</b> - 0973556590
                        <br />
                        So 1 Ngo 2 - Quận Cầu Giấy - Hà Nội
                      </label>
                    </div>
                    <div className="radio">
                      <label className="radio">
                        <input type="radio" name="toasts" />
                        Thêm địa chỉ nhận hàng
                      </label>
                    </div>
                  </div>
                </p>
                <p className="font-bold">Example form with custom validation on each form control</p>
                <form role="form" id="form">
                  <div className="form-group">
                    <label>Email</label>{' '}
                    <input type="email" placeholder="Enter email" className="form-control" required aria-required="true" />
                  </div>
                  <div className="form-group">
                    <label>Password</label> <input type="password" placeholder="Password" className="form-control" name="password" />
                  </div>
                  <div className="form-group">
                    <label>Url</label> <input type="text" placeholder="Enter email" className="form-control" name="url" />
                  </div>
                  <div className="form-group">
                    <label>Number</label> <input type="text" placeholder="Enter email" className="form-control" name="number" />
                  </div>
                  <div className="form-group">
                    <label>MinLength</label> <input type="text" placeholder="Enter email" className="form-control" name="min" />
                  </div>
                  <div className="form-group">
                    <label>MaxLength</label> <input type="text" placeholder="Enter email" className="form-control" name="max" />
                  </div>
                  <div>
                    <button className="btn btn-sm btn-primary m-t-n-xs" type="submit">
                      <strong>Submit</strong>
                    </button>
                  </div>
                </form>
              </div>
            </div>
            <div className="col-xs-12 col-md-6">
              <div className="row wrapper wrapper-content">
                {shoppingCartList.map((shoppingCart, ii) => (
                  <div key={`entity-${ii}`}>
                    <div className="col-xs-12">
                      <div className="ibox float-e-margins">
                        <div className="ibox-title">
                          <h5>{`${shoppingCart.aliwangwang}`}</h5>
                          <div className="ibox-tools">
                            <span className="label label-warning-light pull-right">
                              {`${shoppingCart.items.length}`} mặt hàng trong giỏ
                            </span>
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
          </div>
        </div>
      </>
    );
  }
}

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  shoppingCartList: storeState.shoppingCart.entities,
  isAuthenticated: storeState.authentication.isAuthenticated
});

const mapDispatchToProps = { getSession, getOwnerEntities };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Checkout);
