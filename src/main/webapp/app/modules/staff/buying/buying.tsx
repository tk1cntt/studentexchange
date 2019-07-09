import React from 'react';
import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';

import { Checkbox } from 'antd';
import qs from 'query-string';

import { getSession } from 'app/shared/reducers/authentication';
import { getEntity as getOrder } from 'app/entities/order-cart/order-cart.reducer';
import { formatCurency, encodeId, decodeId } from 'app/shared/util/utils';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';

export interface IBuyingProp extends StateProps, DispatchProps {
  location: any;
  history: any;
}

export class Buying extends React.Component<IBuyingProp> {
  componentDidMount() {
    if (this.props.location) {
      const parsed = qs.parse(this.props.location.search);
      this.props.getOrder(decodeId(parsed.orderid));
    }
  }

  render() {
    const { orderCartEntity } = this.props;
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="shopping-cart" activeSubMenu="" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row border-bottom white-bg dashboard-header">
            <h4>
              Chi tiết đơn hàng <b>{orderCartEntity.code}</b>
            </h4>
          </div>
          <div className="row">
            <div className="col-xs-12">
              <div className="row wrapper wrapper-content animated fadeInRight">
                {orderCartEntity && !orderCartEntity.id ? (
                  <div className="ibox">
                    <div className="ibox-content">
                      <div className="no-content">Không có hàng</div>
                    </div>
                  </div>
                ) : (
                  <div key={`entity`}>
                    <div className="col-xs-12 col-md-8">
                      <div className="ibox float-e-margins">
                        <div className="ibox-title">
                          <h5>
                            <a href={orderCartEntity.shopLink}>{`${orderCartEntity.aliwangwang}`}</a>
                          </h5>
                          <div className="ibox-tools">
                            <span className="label label-warning-light pull-right">
                              {`${orderCartEntity.items && orderCartEntity.items.length}`} mặt hàng trong giỏ
                            </span>
                          </div>
                        </div>
                        <div className="ibox-content">
                          <div>
                            <div className="feed-activity-list">
                              {orderCartEntity.items &&
                                orderCartEntity.items.map((item, iy) => (
                                  <div className="feed-element" key={`entity-${iy}`}>
                                    <a href={item.itemLink} className="pull-left">
                                      <img
                                        alt="image"
                                        className="img-circle"
                                        src={`${item.propertiesImage ? item.propertiesImage : item.itemImage}`}
                                      />
                                    </a>
                                    <div className="media-body ">
                                      <small className="pull-right">
                                        <div className="input-group bootstrap-touchspin">
                                          <input
                                            type="tel"
                                            className="form-control quantity"
                                            disabled
                                            min="1"
                                            defaultValue={`${item.quantity}`}
                                          />
                                        </div>
                                      </small>
                                      <a href={item.itemLink}>
                                        <strong>{`${item.itemName}`}</strong>
                                      </a>
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
                    <div className="col-xs-12 col-md-4">
                      <div className="row checkout-cart-detail">
                        <span className="checkout-cart">
                          <button className="btn btn-primary btn-block">
                            <i className="fa fa-shopping-cart" /> Hoàn tất mua hàng
                          </button>
                        </span>
                        <ul className="list-group clear-list m-t">
                          <li className="list-group-item">
                            <span className="pull-right">
                              <b>¥{formatCurency(orderCartEntity.totalAmountNDT)}</b>
                            </span>
                            Tiền hàng NDT:
                          </li>
                          <li className="list-group-item">
                            <span className="pull-right">
                              <b>{formatCurency(orderCartEntity.totalAmount)}đ</b>
                            </span>
                            Tiền hàng VNĐ:
                          </li>
                          <li className="list-group-item">
                            <span className="pull-right">
                              <b>0đ</b>
                            </span>
                            Phí vận chuyển nội địa TQ:
                          </li>
                          <li className="list-group-item">
                            <span className="pull-right">
                              <b>0đ</b>
                            </span>
                            Mã đơn hàng trên trang <b className="text-warning">{orderCartEntity.website}</b>:
                          </li>
                        </ul>
                        <span className="checkout-cart">
                          <button className="btn btn-danger btn-block">
                            <i className="fa fa-shopping-cart" /> Huỷ đơn hàng
                          </button>
                        </span>
                      </div>
                    </div>
                  </div>
                )}
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
  orderCartEntity: storeState.orderCart.entity,
  isAuthenticated: storeState.authentication.isAuthenticated
});

const mapDispatchToProps = { getSession, getOrder };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Buying);
