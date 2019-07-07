import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './order-cart.reducer';
import { IOrderCart } from 'app/shared/model/order-cart.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrderCartDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class OrderCartDetail extends React.Component<IOrderCartDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { orderCartEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="studentexchangeApp.orderCart.detail.title">OrderCart</Translate> [<b>{orderCartEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">
                <Translate contentKey="studentexchangeApp.orderCart.code">Code</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.code}</dd>
            <dt>
              <span id="avatar">
                <Translate contentKey="studentexchangeApp.orderCart.avatar">Avatar</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.avatar}</dd>
            <dt>
              <span id="aliwangwang">
                <Translate contentKey="studentexchangeApp.orderCart.aliwangwang">Aliwangwang</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.aliwangwang}</dd>
            <dt>
              <span id="amountDiscount">
                <Translate contentKey="studentexchangeApp.orderCart.amountDiscount">Amount Discount</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.amountDiscount}</dd>
            <dt>
              <span id="amountPaid">
                <Translate contentKey="studentexchangeApp.orderCart.amountPaid">Amount Paid</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.amountPaid}</dd>
            <dt>
              <span id="depositAmount">
                <Translate contentKey="studentexchangeApp.orderCart.depositAmount">Deposit Amount</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.depositAmount}</dd>
            <dt>
              <span id="depositRatio">
                <Translate contentKey="studentexchangeApp.orderCart.depositRatio">Deposit Ratio</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.depositRatio}</dd>
            <dt>
              <span id="depositTime">
                <Translate contentKey="studentexchangeApp.orderCart.depositTime">Deposit Time</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={orderCartEntity.depositTime} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="domesticShippingChinaFeeNDT">
                <Translate contentKey="studentexchangeApp.orderCart.domesticShippingChinaFeeNDT">Domestic Shipping China Fee NDT</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.domesticShippingChinaFeeNDT}</dd>
            <dt>
              <span id="domesticShippingChinaFee">
                <Translate contentKey="studentexchangeApp.orderCart.domesticShippingChinaFee">Domestic Shipping China Fee</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.domesticShippingChinaFee}</dd>
            <dt>
              <span id="domesticShippingVietnamFee">
                <Translate contentKey="studentexchangeApp.orderCart.domesticShippingVietnamFee">Domestic Shipping Vietnam Fee</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.domesticShippingVietnamFee}</dd>
            <dt>
              <span id="quantityOrder">
                <Translate contentKey="studentexchangeApp.orderCart.quantityOrder">Quantity Order</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.quantityOrder}</dd>
            <dt>
              <span id="quantityPending">
                <Translate contentKey="studentexchangeApp.orderCart.quantityPending">Quantity Pending</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.quantityPending}</dd>
            <dt>
              <span id="quantityReceived">
                <Translate contentKey="studentexchangeApp.orderCart.quantityReceived">Quantity Received</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.quantityReceived}</dd>
            <dt>
              <span id="rate">
                <Translate contentKey="studentexchangeApp.orderCart.rate">Rate</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.rate}</dd>
            <dt>
              <span id="receiverName">
                <Translate contentKey="studentexchangeApp.orderCart.receiverName">Receiver Name</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.receiverName}</dd>
            <dt>
              <span id="receiverAddress">
                <Translate contentKey="studentexchangeApp.orderCart.receiverAddress">Receiver Address</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.receiverAddress}</dd>
            <dt>
              <span id="receiverMobile">
                <Translate contentKey="studentexchangeApp.orderCart.receiverMobile">Receiver Mobile</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.receiverMobile}</dd>
            <dt>
              <span id="receiverNote">
                <Translate contentKey="studentexchangeApp.orderCart.receiverNote">Receiver Note</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.receiverNote}</dd>
            <dt>
              <span id="refundAmountByAlipay">
                <Translate contentKey="studentexchangeApp.orderCart.refundAmountByAlipay">Refund Amount By Alipay</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.refundAmountByAlipay}</dd>
            <dt>
              <span id="refundAmountByComplaint">
                <Translate contentKey="studentexchangeApp.orderCart.refundAmountByComplaint">Refund Amount By Complaint</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.refundAmountByComplaint}</dd>
            <dt>
              <span id="refundAmountByOrder">
                <Translate contentKey="studentexchangeApp.orderCart.refundAmountByOrder">Refund Amount By Order</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.refundAmountByOrder}</dd>
            <dt>
              <span id="refundAmountPending">
                <Translate contentKey="studentexchangeApp.orderCart.refundAmountPending">Refund Amount Pending</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.refundAmountPending}</dd>
            <dt>
              <span id="shippingChinaVietnamFee">
                <Translate contentKey="studentexchangeApp.orderCart.shippingChinaVietnamFee">Shipping China Vietnam Fee</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.shippingChinaVietnamFee}</dd>
            <dt>
              <span id="shippingChinaVietnamFeeDiscount">
                <Translate contentKey="studentexchangeApp.orderCart.shippingChinaVietnamFeeDiscount">
                  Shipping China Vietnam Fee Discount
                </Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.shippingChinaVietnamFeeDiscount}</dd>
            <dt>
              <span id="serviceFee">
                <Translate contentKey="studentexchangeApp.orderCart.serviceFee">Service Fee</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.serviceFee}</dd>
            <dt>
              <span id="serviceFeeDiscount">
                <Translate contentKey="studentexchangeApp.orderCart.serviceFeeDiscount">Service Fee Discount</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.serviceFeeDiscount}</dd>
            <dt>
              <span id="itemChecking">
                <Translate contentKey="studentexchangeApp.orderCart.itemChecking">Item Checking</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.itemChecking ? 'true' : 'false'}</dd>
            <dt>
              <span id="itemWoodCrating">
                <Translate contentKey="studentexchangeApp.orderCart.itemWoodCrating">Item Wood Crating</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.itemWoodCrating ? 'true' : 'false'}</dd>
            <dt>
              <span id="shopId">
                <Translate contentKey="studentexchangeApp.orderCart.shopId">Shop Id</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.shopId}</dd>
            <dt>
              <span id="shopLink">
                <Translate contentKey="studentexchangeApp.orderCart.shopLink">Shop Link</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.shopLink}</dd>
            <dt>
              <span id="shopName">
                <Translate contentKey="studentexchangeApp.orderCart.shopName">Shop Name</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.shopName}</dd>
            <dt>
              <span id="shopNote">
                <Translate contentKey="studentexchangeApp.orderCart.shopNote">Shop Note</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.shopNote}</dd>
            <dt>
              <span id="website">
                <Translate contentKey="studentexchangeApp.orderCart.website">Website</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.website}</dd>
            <dt>
              <span id="status">
                <Translate contentKey="studentexchangeApp.orderCart.status">Status</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.status}</dd>
            <dt>
              <span id="statusName">
                <Translate contentKey="studentexchangeApp.orderCart.statusName">Status Name</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.statusName}</dd>
            <dt>
              <span id="statusStyle">
                <Translate contentKey="studentexchangeApp.orderCart.statusStyle">Status Style</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.statusStyle}</dd>
            <dt>
              <span id="tallyFee">
                <Translate contentKey="studentexchangeApp.orderCart.tallyFee">Tally Fee</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.tallyFee}</dd>
            <dt>
              <span id="totalAmount">
                <Translate contentKey="studentexchangeApp.orderCart.totalAmount">Total Amount</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.totalAmount}</dd>
            <dt>
              <span id="totalAmountNDT">
                <Translate contentKey="studentexchangeApp.orderCart.totalAmountNDT">Total Amount NDT</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.totalAmountNDT}</dd>
            <dt>
              <span id="totalPaidByCustomer">
                <Translate contentKey="studentexchangeApp.orderCart.totalPaidByCustomer">Total Paid By Customer</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.totalPaidByCustomer}</dd>
            <dt>
              <span id="totalServiceFee">
                <Translate contentKey="studentexchangeApp.orderCart.totalServiceFee">Total Service Fee</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.totalServiceFee}</dd>
            <dt>
              <span id="totalQuantity">
                <Translate contentKey="studentexchangeApp.orderCart.totalQuantity">Total Quantity</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.totalQuantity}</dd>
            <dt>
              <span id="finalAmount">
                <Translate contentKey="studentexchangeApp.orderCart.finalAmount">Final Amount</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.finalAmount}</dd>
            <dt>
              <span id="orderName">
                <Translate contentKey="studentexchangeApp.orderCart.orderName">Order Name</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.orderName}</dd>
            <dt>
              <span id="orderAddress">
                <Translate contentKey="studentexchangeApp.orderCart.orderAddress">Order Address</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.orderAddress}</dd>
            <dt>
              <span id="orderMobile">
                <Translate contentKey="studentexchangeApp.orderCart.orderMobile">Order Mobile</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.orderMobile}</dd>
            <dt>
              <span id="orderNote">
                <Translate contentKey="studentexchangeApp.orderCart.orderNote">Order Note</Translate>
              </span>
            </dt>
            <dd>{orderCartEntity.orderNote}</dd>
            <dt>
              <span id="createAt">
                <Translate contentKey="studentexchangeApp.orderCart.createAt">Create At</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={orderCartEntity.createAt} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updateAt">
                <Translate contentKey="studentexchangeApp.orderCart.updateAt">Update At</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={orderCartEntity.updateAt} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="studentexchangeApp.orderCart.buyer">Buyer</Translate>
            </dt>
            <dd>{orderCartEntity.buyerLogin ? orderCartEntity.buyerLogin : ''}</dd>
            <dt>
              <Translate contentKey="studentexchangeApp.orderCart.chinaStocker">China Stocker</Translate>
            </dt>
            <dd>{orderCartEntity.chinaStockerLogin ? orderCartEntity.chinaStockerLogin : ''}</dd>
            <dt>
              <Translate contentKey="studentexchangeApp.orderCart.vietnamStocker">Vietnam Stocker</Translate>
            </dt>
            <dd>{orderCartEntity.vietnamStockerLogin ? orderCartEntity.vietnamStockerLogin : ''}</dd>
            <dt>
              <Translate contentKey="studentexchangeApp.orderCart.exporter">Exporter</Translate>
            </dt>
            <dd>{orderCartEntity.exporterLogin ? orderCartEntity.exporterLogin : ''}</dd>
            <dt>
              <Translate contentKey="studentexchangeApp.orderCart.createBy">Create By</Translate>
            </dt>
            <dd>{orderCartEntity.createByLogin ? orderCartEntity.createByLogin : ''}</dd>
            <dt>
              <Translate contentKey="studentexchangeApp.orderCart.updateBy">Update By</Translate>
            </dt>
            <dd>{orderCartEntity.updateByLogin ? orderCartEntity.updateByLogin : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/order-cart" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/order-cart/${orderCartEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ orderCart }: IRootState) => ({
  orderCartEntity: orderCart.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OrderCartDetail);
