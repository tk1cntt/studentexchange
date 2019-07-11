import React from 'react';
import { Steps } from 'antd';
import { currentOrderStep } from 'app/shared/util/utils';
import { OrderStatus } from 'app/shared/model/order-cart.model';

const { Step } = Steps;

export interface IOrderStatusListProp {
  orderCartEntity: any;
}

export class OrderStatusList extends React.Component<IOrderStatusListProp> {
  render() {
    const { orderCartEntity } = this.props;
    const currentStep = currentOrderStep(orderCartEntity.status);
    return (
      <>
        <span className="checkout-cart">
          <button className="btn btn-warning btn-block">Trạng thái đơn hàng</button>
        </span>
        {orderCartEntity.status === OrderStatus.CANCELLED ? (
          <div className="order-status-steps">
            <Steps direction="vertical" size="small" current={currentStep}>
              <Step title="DEPOSITED" description="Đã đặt cọc." />
              <Step title="CANCELLED" description="Đã huỷ." />
            </Steps>
          </div>
        ) : (
          <div className="order-status-steps">
            <Steps direction="vertical" size="small" current={currentStep}>
              <Step title="Đã đặt cọc." />
              <Step title="Đang mua hàng." />
              <Step title="Đã mua hàng." />
              <Step title="Người bán giao hàng." />
              <Step title="Hàng về kho Trung Quốc." />
              <Step title="Chuyển hàng từ TQ về VN." />
              <Step title="Hàng về kho Việt Nam." />
              <Step title="Yêu cầu giao hàng." />
              <Step title="Đang giao hàng." />
              <Step title="Đã giao hàng." />
            </Steps>
          </div>
        )}
      </>
    );
  }
}

export default OrderStatusList;
