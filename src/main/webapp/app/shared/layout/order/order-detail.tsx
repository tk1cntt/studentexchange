import React from 'react';
import { connect } from 'react-redux';
import { Modal } from 'antd';

import qs from 'query-string';

import { getSession } from 'app/shared/reducers/authentication';
import { getEntity as getOrder, updatePurchased, updateCancel } from 'app/entities/order-cart/order-cart.reducer';
import { decodeId } from 'app/shared/util/utils';
import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';
import Footer from 'app/shared/layout/footer/footer';
import OrderItemListView from 'app/shared/layout/order/order-item-list-view';
import OrderStatusList from 'app/shared/layout/order/order-status-list-view';
import OrderPaymentInfo from 'app/shared/layout/order/order-payment-info-view';
import OrderCancelReason from 'app/shared/layout/order/order-cancel-reason-view';

export interface IOrderDetailProp extends StateProps, DispatchProps {
  location?: any;
  history?: any;
  goto?: string;
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
      console.log('OrderDetail', parsed);
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

  onChangeTotalAmountChinaNDT = e => {
    this.setState({
      totalAmountChinaNDT: e.target.value
    });
  };

  /*
  doFinishOrder = () => {
    if (!this.state.shippingChinaCode) {
      Modal.error({
        title: 'Cảnh báo',
        content: `Hãy nhập mã đơn hàng trên trang ${this.props.orderCartEntity.website}`
      });
    } else if (!(Number(this.state.totalAmountChinaNDT) > 0)) {
      Modal.error({
        title: 'Cảnh báo',
        content: `Hãy nhập giá trị đơn hàng đã mua hộ`
      });
    } else if (Number(this.state.totalAmountChinaNDT) > Number(this.props.orderCartEntity.totalAmountNDT)) {
      Modal.error({
        title: 'Cảnh báo',
        content: `Đơn hàng mua hộ giá đang vượt quá số tiền cho phép ¥${Math.ceil(
          Number(this.state.totalAmountChinaNDT) - Number(this.props.orderCartEntity.totalAmountNDT)
        )}`
      });
    } else {
      const entity = {
        id: this.props.orderCartEntity.id,
        domesticShippingChinaFeeNDT: this.state.domesticShippingChinaFeeNDT,
        shippingChinaCode: this.state.shippingChinaCode,
        totalAmountChinaNDT: this.state.totalAmountChinaNDT
      };
      this.props.updatePurchased(entity);
      this.props.history.push('/staff/order-purchased');
    }
  };
  */

  showCancelOrder = () => {
    this.setState({
      showCancelOrder: true
    });
  };

  doCancelOrder = () => {
    if (!this.state.cancelReason) {
      Modal.error({
        title: 'Cảnh báo',
        content: `Hãy chọn lý do huỷ đơn hàng`
      });
    } else {
      const entity = {
        id: this.props.orderCartEntity.id,
        statusName: this.state.cancelReason
      };
      this.props.updateCancel(entity);
      if (!this.props.goto) {
        this.props.history.push('/staff/order-cancel');
      } else {
        this.props.history.push(this.props.goto);
      }
    }
  };

  closeCancelOrder = () => {
    this.setState({
      showCancelOrder: false
    });
  };

  selectCancelReason = value => {
    this.setState({
      cancelReason: value
    });
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
                ) : (
                  <div key={`entity`}>
                    <div className="col-xs-12 col-md-6">
                      <OrderItemListView isAuthenticated={this.props.isAuthenticated} orderCartEntity={this.props.orderCartEntity} />
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
                          <OrderCancelReason onChange={this.selectCancelReason} />
                        </Modal>
                      ) : (
                        ''
                      )}
                    </div>
                    <div className="col-xs-12 col-md-3">
                      <OrderPaymentInfo orderCartEntity={this.props.orderCartEntity} />
                    </div>
                    <div className="col-xs-12 col-md-3">
                      <div className="checkout-cart-detail">
                        <OrderStatusList orderCartEntity={this.props.orderCartEntity} />
                      </div>
                    </div>
                  </div>
                )}
              </div>
              <div className="footer">
                <div className="col-xs-12">
                  <Footer />
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

const mapDispatchToProps = { getSession, getOrder, updatePurchased, updateCancel };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OrderDetail);
