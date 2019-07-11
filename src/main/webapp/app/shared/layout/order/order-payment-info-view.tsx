import React from 'react';
import { formatCurency } from 'app/shared/util/utils';

export interface IOrderPaymentInfoProp {
  orderCartEntity: any;
}

export class OrderPaymentInfo extends React.Component<IOrderPaymentInfoProp> {
  render() {
    const { orderCartEntity } = this.props;
    return (
      <>
        <div className="row checkout-cart-detail">
          <span className="checkout-cart">
            <button className="btn btn-primary btn-block">
              <i className="fa fa-cash" /> Thông tin thanh toán
            </button>
          </span>
          <ul className="list-group clear-list m-t">
            <li className="list-group-item">
              <span className="pull-right">
                <b>{formatCurency(orderCartEntity.totalAmount)}đ</b>
              </span>
              Tiền hàng:
            </li>
            <li className="list-group-item">
              <span className="pull-right">
                <b>{formatCurency(orderCartEntity.serviceFee)}đ</b>
              </span>
              Phí mua hàng:
            </li>
            <li className="list-group-item">
              <span className="pull-right">
                <b>{formatCurency(orderCartEntity.tallyFee)}đ</b>
              </span>
              Phí kiểm đếm:
            </li>
            <li className="list-group-item">
              <span className="pull-right">
                <b>{formatCurency(orderCartEntity.domesticShippingChinaFee)}đ</b>
              </span>
              Phí vận chuyển nội địa TQ:
            </li>
            <li className="list-group-item">
              <span className="pull-right">
                <b>0đ</b>
              </span>
              Phí đóng kiện gỗ:
            </li>
            <li className="list-group-item">
              <span className="pull-right">
                <b>0đ</b>
              </span>
              Phí vận chuyển TQ - VN:
            </li>
            <li className="list-group-item">
              <span className="pull-right">
                <b>0đ</b>
              </span>
              Phí vận chuyển nội địa VN:
            </li>
          </ul>
        </div>
      </>
    );
  }
}

export default OrderPaymentInfo;
