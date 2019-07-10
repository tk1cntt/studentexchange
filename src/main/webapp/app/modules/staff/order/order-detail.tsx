import React from 'react';
import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';

import { Modal, Select } from 'antd';

import qs from 'query-string';

import { getSession } from 'app/shared/reducers/authentication';
import { getEntity as getOrder, updatePurchased } from 'app/entities/order-cart/order-cart.reducer';
import { formatCurency, encodeId, decodeId } from 'app/shared/util/utils';
import { OrderStatus } from 'app/shared/model/order-cart.model';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';

const { Option } = Select;

export interface IOrderDetailProp extends StateProps, DispatchProps {
  location: any;
  history: any;
}

export interface IOrderDetailState {
  showCancelOrder: boolean;
  cancelReason: string;
  domesticShippingChinaFeeNDT: number;
  shippingChinaCode: string;
}

export class OrderDetail extends React.Component<IOrderDetailProp> {
  state: IOrderDetailState = {
    showCancelOrder: false,
    cancelReason: null,
    domesticShippingChinaFeeNDT: 0,
    shippingChinaCode: null
  };

  componentDidMount() {
    if (this.props.location) {
      const parsed = qs.parse(this.props.location.search);
      this.props.getOrder(decodeId(parsed.orderid));
    }
  }

  onChangeDomesticShippingChinaFeeNDT = e => {
    this.setState({
      domesticShippingChinaFeeNDT: e.target.value
    });
  };

  onChangeShippingChinaCode = e => {
    this.setState({
      shippingChinaCode: e.target.value
    });
  };

  doFinishOrder = () => {
    if (!this.state.shippingChinaCode) {
      Modal.error({
        title: 'Cảnh báo',
        content: `Hãy  nhập mã đơn hàng trên trang ${this.props.orderCartEntity.website}`
      });
    } else {
      const entity = {
        id: this.props.orderCartEntity.id,
        domesticShippingChinaFeeNDT: this.state.domesticShippingChinaFeeNDT,
        shippingChinaCode: this.state.shippingChinaCode
      };
      this.props.updatePurchased(entity);
      this.props.history.push('/staff/order-purchased');
    }
  };

  showCancelOrder = () => {
    this.setState({
      showCancelOrder: true
    });
  };

  doCancelOrder = () => {};

  closeCancelOrder = () => {
    this.setState({
      showCancelOrder: false
    });
  };

  selectCancelReason = e => {
    // console.log(e);
  };

  render() {
    const { orderCartEntity } = this.props;
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="order-management" activeSubMenu="order-detail" />
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
                      <div className="no-content">Đơn hàng không tồn tại</div>
                    </div>
                  </div>
                ) : orderCartEntity.status !== OrderStatus.ARE_BUYING ? (
                  <div className="ibox">
                    <div className="ibox-content">
                      <div className="no-content">Đơn hàng đang được xử lý</div>
                    </div>
                  </div>
                ) : (
                  <div key={`entity`}>
                    <div className="col-xs-12 col-md-8">
                      <div className="ibox float-e-margins">
                        <div className="ibox-title">
                          <h5>
                            <a href={orderCartEntity.shopLink} target="_blank">{`${orderCartEntity.aliwangwang}`}</a>
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
                                    <a href={item.itemLink} className="pull-left" target="_blank">
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
                                      <a href={item.itemLink} target="_blank">
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
                          <button className="btn btn-primary btn-block" onClick={this.doFinishOrder}>
                            <i className="fa fa-check" /> Hoàn tất mua hàng
                          </button>
                        </span>
                        <ul className="list-group clear-list m-t">
                          <li className="list-group-item">
                            <span className="pull-right">
                              <b>{formatCurency(orderCartEntity.rate)}đ</b>
                            </span>
                            Tỷ giá của đơn hàng:
                          </li>
                          <li className="list-group-item">
                            <span className="pull-right">
                              <b>{formatCurency(orderCartEntity.totalAmountNDT)}đ</b>
                            </span>
                            Tiền hàng NDT:
                          </li>
                          <li className="list-group-item">
                            <div className="form-group">
                              <label>Phí vận chuyển nội địa TQ</label>
                              <input
                                type="number"
                                placeholder="Nhập phí vận chuyển nếu có"
                                className="form-control"
                                onChange={this.onChangeDomesticShippingChinaFeeNDT}
                              />
                            </div>
                          </li>
                          <li className="list-group-item">
                            <div className="form-group">
                              <label>
                                Mã vận đơn trên trang <b className="text-warning">{orderCartEntity.website}</b>
                              </label>
                              <input
                                type="text"
                                placeholder="Nhập mã vận đơn"
                                className="form-control"
                                onChange={this.onChangeShippingChinaCode}
                              />
                            </div>
                          </li>
                        </ul>
                        <span className="checkout-cart">
                          <button className="btn btn-danger btn-block" onClick={this.showCancelOrder}>
                            <i className="fa fa-window-close" /> Huỷ đơn hàng
                          </button>
                        </span>
                        {this.state.showCancelOrder ? (
                          <Modal
                            title={`Huỷ đơn hàng ${orderCartEntity.code}`}
                            visible={this.state.showCancelOrder}
                            okText="Huỷ đơn hàng"
                            okType="danger"
                            cancelText="Bỏ qua"
                            onOk={this.doCancelOrder}
                            onCancel={this.closeCancelOrder}
                          >
                            <p>Lý do huỷ đơn hàng</p>
                            <div className="form-group">
                              <Select className="btn-block" onChange={this.selectCancelReason}>
                                <Option value="PRICE_HAS_CHANGED">Giá mặt hàng thay đổi</Option>
                                <Option value="OUT_OF_STOCK">Hết hàng</Option>
                                <Option value="WRONG_INFO">Thông tin đơn hàng không chính xác</Option>
                                <Option value="OTHER">Lý do khác</Option>
                              </Select>
                            </div>
                          </Modal>
                        ) : (
                          ''
                        )}
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

const mapDispatchToProps = { getSession, getOrder, updatePurchased };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OrderDetail);
