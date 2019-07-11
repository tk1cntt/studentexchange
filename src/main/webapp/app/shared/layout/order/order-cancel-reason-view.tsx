import React from 'react';
import { Select } from 'antd';
const { Option } = Select;

export interface IOrderStatusListProp {
  onChange: any;
}

export class OrderStatusList extends React.Component<IOrderStatusListProp> {
  render() {
    return (
      <>
        <p>Lý do huỷ đơn hàng</p>
        <div className="form-group">
          <Select className="btn-block" onChange={this.props.onChange}>
            <Option value="PRICE_HAS_CHANGED">Giá mặt hàng thay đổi</Option>
            <Option value="OUT_OF_STOCK">Hết hàng</Option>
            <Option value="WRONG_INFO">Thông tin đơn hàng không chính xác</Option>
            <Option value="OTHER">Lý do khác</Option>
          </Select>
        </div>
      </>
    );
  }
}

export default OrderStatusList;
