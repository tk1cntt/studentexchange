import React from 'react';
import { Steps } from 'antd';
import { currentOrderStep } from 'app/shared/util/utils';

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
        <div className="order-status-steps">
          <Steps direction="vertical" size="small" current={currentStep}>
            <Step title="DEPOSITED" description="Đã đặt cọc." />
            <Step title="ARE_BUYING" description="Đang mua hàng." />
            <Step title="PURCHASED" description="Đã mua hàng." />
            <Step title="SELLER_DELIVERY" description="Người bán giao hàng." />
            <Step title="WAREHOUSE_CHINA" description="Hàng về kho Trung Quốc." />
            <Step title="DELIVERING_CHINA_VIETNAM" description="Chuyển hàng từ Trung Quốc về Việt Nam." />
            <Step title="WAREHOUSE_VIETNAM" description="Hàng về kho Việt Nam." />
            <Step title="DELIVERY_REQUIREMENTS" description="Yêu cầu giao hàng." />
            <Step title="DELIVERING_VIETNAM" description="Đang chuyển hàng." />
            <Step title="DELIVERED" description="Đã giao hàng." />
          </Steps>
        </div>
      </>
    );
  }
}

export default OrderStatusList;
