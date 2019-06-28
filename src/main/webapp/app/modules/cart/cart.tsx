import React from 'react';
import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';

import { getSession } from 'app/shared/reducers/authentication';
import { getOwnerEntities } from 'app/entities/shopping-cart/shopping-cart.reducer';
import { formatCurency, encodeId } from 'app/shared/util/utils';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';

export interface IHomeProp extends StateProps, DispatchProps {}

export class Cart extends React.Component<IHomeProp> {
  componentDidMount() {
    this.props.getSession();
    this.props.getOwnerEntities();
  }

  decreaseQuantity = (item: any) => {
    // console.log(item);
  };

  render() {
    const { shoppingCartList, account } = this.props;
    // console.log('shoppingCartList', shoppingCartList);
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="shopping-cart" activeSubMenu="" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row border-bottom white-bg dashboard-header">
            Noi dung phia tren
            <Link to={'/checkout'}>
              <button className="btn btn-primary btn-block m-t checkout-cart">
                <i className="fa fa-shopping-cart" /> Đặt tất cả hàng
              </button>
            </Link>
          </div>
          <div className="row">
            <div className="col-xs-12">
              <div className="row wrapper wrapper-content animated fadeInRight">
                {shoppingCartList.map((shoppingCart, ii) => (
                  <div key={`entity-${ii}`}>
                    <div className=".col-xs-12 col-md-8">
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
                                    <img
                                      alt="image"
                                      className="img-circle"
                                      src={`${item.propertiesImage ? item.propertiesImage : item.itemImage}`}
                                    />
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
                    <div className=".col-xs-12 col-md-4">
                      <div className="row checkout-cart-detail">
                        <span className="checkout-cart">
                          <Link to={`/checkout?shopid=${encodeId(1)}`}>
                            <button className="btn btn-primary btn-block">
                              <i className="fa fa-shopping-cart" /> Đặt hàng
                            </button>
                          </Link>
                        </span>
                        <div className="col-xs-8 item">Tiền hàng:</div>
                        <div className="col-xs-4 item">
                          <b>{formatCurency(shoppingCart.totalAmount)}đ</b>
                        </div>
                        <div className="col-xs-8 item">Phí mua hàng:</div>
                        <div className="col-xs-4 item">
                          <b>{formatCurency(shoppingCart.serviceFee)}đ</b>
                        </div>
                        <div className="col-xs-8 item">Phí kiểm đếm:</div>
                        <div className="col-xs-4 item">
                          <b>{formatCurency(shoppingCart.tallyFee)}đ</b>
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
                          <b>
                            {formatCurency(
                              shoppingCart.totalAmount + shoppingCart.serviceFee + (shoppingCart.tallyFee ? shoppingCart.tallyFee : 0)
                            )}
                            đ
                          </b>
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
)(Cart);
