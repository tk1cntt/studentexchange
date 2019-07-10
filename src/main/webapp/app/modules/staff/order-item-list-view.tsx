import React from 'react';

export interface IOrderListItemProp {
  orderCartEntity: any;
  isAuthenticated: boolean;
}

export class OrderListItem extends React.Component<IOrderListItemProp> {
  render() {
    const { orderCartEntity } = this.props;
    return (
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
                      <img alt="image" className="img-circle" src={`${item.propertiesImage ? item.propertiesImage : item.itemImage}`} />
                    </a>
                    <div className="media-body ">
                      <small className="pull-right">
                        <div className="input-group bootstrap-touchspin">
                          <input type="tel" className="form-control quantity" disabled min="1" defaultValue={`${item.quantity}`} />
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
    );
  }
}

export default OrderListItem;
