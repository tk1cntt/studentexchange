import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntity, updateEntity, createEntity, reset } from './order-cart.reducer';
import { IOrderCart } from 'app/shared/model/order-cart.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOrderCartUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IOrderCartUpdateState {
  isNew: boolean;
  buyerId: string;
  chinaStockerId: string;
  vietnamStockerId: string;
  exporterId: string;
  createById: string;
  updateById: string;
}

export class OrderCartUpdate extends React.Component<IOrderCartUpdateProps, IOrderCartUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      buyerId: '0',
      chinaStockerId: '0',
      vietnamStockerId: '0',
      exporterId: '0',
      createById: '0',
      updateById: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getUsers();
  }

  saveEntity = (event, errors, values) => {
    values.depositTime = new Date(values.depositTime);
    values.createAt = new Date(values.createAt);
    values.updateAt = new Date(values.updateAt);

    if (errors.length === 0) {
      const { orderCartEntity } = this.props;
      const entity = {
        ...orderCartEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/order-cart');
  };

  render() {
    const { orderCartEntity, users, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="studentexchangeApp.orderCart.home.createOrEditLabel">
              <Translate contentKey="studentexchangeApp.orderCart.home.createOrEditLabel">Create or edit a OrderCart</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : orderCartEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="order-cart-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeLabel" for="code">
                    <Translate contentKey="studentexchangeApp.orderCart.code">Code</Translate>
                  </Label>
                  <AvField id="order-cart-code" type="string" className="form-control" name="code" />
                </AvGroup>
                <AvGroup>
                  <Label id="avatarLabel" for="avatar">
                    <Translate contentKey="studentexchangeApp.orderCart.avatar">Avatar</Translate>
                  </Label>
                  <AvField id="order-cart-avatar" type="text" name="avatar" />
                </AvGroup>
                <AvGroup>
                  <Label id="amountDiscountLabel" for="amountDiscount">
                    <Translate contentKey="studentexchangeApp.orderCart.amountDiscount">Amount Discount</Translate>
                  </Label>
                  <AvField id="order-cart-amountDiscount" type="string" className="form-control" name="amountDiscount" />
                </AvGroup>
                <AvGroup>
                  <Label id="amountPaidLabel" for="amountPaid">
                    <Translate contentKey="studentexchangeApp.orderCart.amountPaid">Amount Paid</Translate>
                  </Label>
                  <AvField id="order-cart-amountPaid" type="string" className="form-control" name="amountPaid" />
                </AvGroup>
                <AvGroup>
                  <Label id="depositAmountLabel" for="depositAmount">
                    <Translate contentKey="studentexchangeApp.orderCart.depositAmount">Deposit Amount</Translate>
                  </Label>
                  <AvField id="order-cart-depositAmount" type="string" className="form-control" name="depositAmount" />
                </AvGroup>
                <AvGroup>
                  <Label id="depositRatioLabel" for="depositRatio">
                    <Translate contentKey="studentexchangeApp.orderCart.depositRatio">Deposit Ratio</Translate>
                  </Label>
                  <AvField id="order-cart-depositRatio" type="string" className="form-control" name="depositRatio" />
                </AvGroup>
                <AvGroup>
                  <Label id="depositTimeLabel" for="depositTime">
                    <Translate contentKey="studentexchangeApp.orderCart.depositTime">Deposit Time</Translate>
                  </Label>
                  <AvInput
                    id="order-cart-depositTime"
                    type="datetime-local"
                    className="form-control"
                    name="depositTime"
                    value={isNew ? null : convertDateTimeFromServer(this.props.orderCartEntity.depositTime)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="domesticShippingChinaFeeNDTLabel" for="domesticShippingChinaFeeNDT">
                    <Translate contentKey="studentexchangeApp.orderCart.domesticShippingChinaFeeNDT">
                      Domestic Shipping China Fee NDT
                    </Translate>
                  </Label>
                  <AvField
                    id="order-cart-domesticShippingChinaFeeNDT"
                    type="string"
                    className="form-control"
                    name="domesticShippingChinaFeeNDT"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="domesticShippingChinaFeeLabel" for="domesticShippingChinaFee">
                    <Translate contentKey="studentexchangeApp.orderCart.domesticShippingChinaFee">Domestic Shipping China Fee</Translate>
                  </Label>
                  <AvField
                    id="order-cart-domesticShippingChinaFee"
                    type="string"
                    className="form-control"
                    name="domesticShippingChinaFee"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="domesticShippingVietnamFeeLabel" for="domesticShippingVietnamFee">
                    <Translate contentKey="studentexchangeApp.orderCart.domesticShippingVietnamFee">
                      Domestic Shipping Vietnam Fee
                    </Translate>
                  </Label>
                  <AvField
                    id="order-cart-domesticShippingVietnamFee"
                    type="string"
                    className="form-control"
                    name="domesticShippingVietnamFee"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="quantityOrderLabel" for="quantityOrder">
                    <Translate contentKey="studentexchangeApp.orderCart.quantityOrder">Quantity Order</Translate>
                  </Label>
                  <AvField id="order-cart-quantityOrder" type="string" className="form-control" name="quantityOrder" />
                </AvGroup>
                <AvGroup>
                  <Label id="quantityPendingLabel" for="quantityPending">
                    <Translate contentKey="studentexchangeApp.orderCart.quantityPending">Quantity Pending</Translate>
                  </Label>
                  <AvField id="order-cart-quantityPending" type="string" className="form-control" name="quantityPending" />
                </AvGroup>
                <AvGroup>
                  <Label id="quantityReceivedLabel" for="quantityReceived">
                    <Translate contentKey="studentexchangeApp.orderCart.quantityReceived">Quantity Received</Translate>
                  </Label>
                  <AvField id="order-cart-quantityReceived" type="string" className="form-control" name="quantityReceived" />
                </AvGroup>
                <AvGroup>
                  <Label id="rateLabel" for="rate">
                    <Translate contentKey="studentexchangeApp.orderCart.rate">Rate</Translate>
                  </Label>
                  <AvField id="order-cart-rate" type="string" className="form-control" name="rate" />
                </AvGroup>
                <AvGroup>
                  <Label id="receiverNameLabel" for="receiverName">
                    <Translate contentKey="studentexchangeApp.orderCart.receiverName">Receiver Name</Translate>
                  </Label>
                  <AvField id="order-cart-receiverName" type="text" name="receiverName" />
                </AvGroup>
                <AvGroup>
                  <Label id="receiverAddressLabel" for="receiverAddress">
                    <Translate contentKey="studentexchangeApp.orderCart.receiverAddress">Receiver Address</Translate>
                  </Label>
                  <AvField id="order-cart-receiverAddress" type="text" name="receiverAddress" />
                </AvGroup>
                <AvGroup>
                  <Label id="receiverMobileLabel" for="receiverMobile">
                    <Translate contentKey="studentexchangeApp.orderCart.receiverMobile">Receiver Mobile</Translate>
                  </Label>
                  <AvField id="order-cart-receiverMobile" type="text" name="receiverMobile" />
                </AvGroup>
                <AvGroup>
                  <Label id="receiverNoteLabel" for="receiverNote">
                    <Translate contentKey="studentexchangeApp.orderCart.receiverNote">Receiver Note</Translate>
                  </Label>
                  <AvField id="order-cart-receiverNote" type="text" name="receiverNote" />
                </AvGroup>
                <AvGroup>
                  <Label id="refundAmountByAlipayLabel" for="refundAmountByAlipay">
                    <Translate contentKey="studentexchangeApp.orderCart.refundAmountByAlipay">Refund Amount By Alipay</Translate>
                  </Label>
                  <AvField id="order-cart-refundAmountByAlipay" type="string" className="form-control" name="refundAmountByAlipay" />
                </AvGroup>
                <AvGroup>
                  <Label id="refundAmountByComplaintLabel" for="refundAmountByComplaint">
                    <Translate contentKey="studentexchangeApp.orderCart.refundAmountByComplaint">Refund Amount By Complaint</Translate>
                  </Label>
                  <AvField id="order-cart-refundAmountByComplaint" type="string" className="form-control" name="refundAmountByComplaint" />
                </AvGroup>
                <AvGroup>
                  <Label id="refundAmountByOrderLabel" for="refundAmountByOrder">
                    <Translate contentKey="studentexchangeApp.orderCart.refundAmountByOrder">Refund Amount By Order</Translate>
                  </Label>
                  <AvField id="order-cart-refundAmountByOrder" type="string" className="form-control" name="refundAmountByOrder" />
                </AvGroup>
                <AvGroup>
                  <Label id="refundAmountPendingLabel" for="refundAmountPending">
                    <Translate contentKey="studentexchangeApp.orderCart.refundAmountPending">Refund Amount Pending</Translate>
                  </Label>
                  <AvField id="order-cart-refundAmountPending" type="string" className="form-control" name="refundAmountPending" />
                </AvGroup>
                <AvGroup>
                  <Label id="shippingChinaVietnamFeeLabel" for="shippingChinaVietnamFee">
                    <Translate contentKey="studentexchangeApp.orderCart.shippingChinaVietnamFee">Shipping China Vietnam Fee</Translate>
                  </Label>
                  <AvField id="order-cart-shippingChinaVietnamFee" type="string" className="form-control" name="shippingChinaVietnamFee" />
                </AvGroup>
                <AvGroup>
                  <Label id="shippingChinaVietnamFeeDiscountLabel" for="shippingChinaVietnamFeeDiscount">
                    <Translate contentKey="studentexchangeApp.orderCart.shippingChinaVietnamFeeDiscount">
                      Shipping China Vietnam Fee Discount
                    </Translate>
                  </Label>
                  <AvField
                    id="order-cart-shippingChinaVietnamFeeDiscount"
                    type="string"
                    className="form-control"
                    name="shippingChinaVietnamFeeDiscount"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="shopAliwangLabel" for="shopAliwang">
                    <Translate contentKey="studentexchangeApp.orderCart.shopAliwang">Shop Aliwang</Translate>
                  </Label>
                  <AvField id="order-cart-shopAliwang" type="text" name="shopAliwang" />
                </AvGroup>
                <AvGroup>
                  <Label id="shopIdLabel" for="shopId">
                    <Translate contentKey="studentexchangeApp.orderCart.shopId">Shop Id</Translate>
                  </Label>
                  <AvField id="order-cart-shopId" type="text" name="shopId" />
                </AvGroup>
                <AvGroup>
                  <Label id="shopLinkLabel" for="shopLink">
                    <Translate contentKey="studentexchangeApp.orderCart.shopLink">Shop Link</Translate>
                  </Label>
                  <AvField id="order-cart-shopLink" type="text" name="shopLink" />
                </AvGroup>
                <AvGroup>
                  <Label id="shopNameLabel" for="shopName">
                    <Translate contentKey="studentexchangeApp.orderCart.shopName">Shop Name</Translate>
                  </Label>
                  <AvField id="order-cart-shopName" type="text" name="shopName" />
                </AvGroup>
                <AvGroup>
                  <Label id="websiteLabel" for="website">
                    <Translate contentKey="studentexchangeApp.orderCart.website">Website</Translate>
                  </Label>
                  <AvField id="order-cart-website" type="text" name="website" />
                </AvGroup>
                <AvGroup>
                  <Label id="websiteCodeLabel" for="websiteCode">
                    <Translate contentKey="studentexchangeApp.orderCart.websiteCode">Website Code</Translate>
                  </Label>
                  <AvField id="order-cart-websiteCode" type="text" name="websiteCode" />
                </AvGroup>
                <AvGroup>
                  <Label id="websiteLadingCodeLabel" for="websiteLadingCode">
                    <Translate contentKey="studentexchangeApp.orderCart.websiteLadingCode">Website Lading Code</Translate>
                  </Label>
                  <AvField id="order-cart-websiteLadingCode" type="text" name="websiteLadingCode" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel">
                    <Translate contentKey="studentexchangeApp.orderCart.status">Status</Translate>
                  </Label>
                  <AvInput
                    id="order-cart-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && orderCartEntity.status) || 'DEPOSITED'}
                  >
                    <option value="DEPOSITED">
                      <Translate contentKey="studentexchangeApp.OrderStatus.DEPOSITED" />
                    </option>
                    <option value="ARE_BUYING">
                      <Translate contentKey="studentexchangeApp.OrderStatus.ARE_BUYING" />
                    </option>
                    <option value="PURCHASED">
                      <Translate contentKey="studentexchangeApp.OrderStatus.PURCHASED" />
                    </option>
                    <option value="SELLER_DELIVERY">
                      <Translate contentKey="studentexchangeApp.OrderStatus.SELLER_DELIVERY" />
                    </option>
                    <option value="WAREHOUSE_CHINA">
                      <Translate contentKey="studentexchangeApp.OrderStatus.WAREHOUSE_CHINA" />
                    </option>
                    <option value="DELIVERING_CHINA_VIETNAM">
                      <Translate contentKey="studentexchangeApp.OrderStatus.DELIVERING_CHINA_VIETNAM" />
                    </option>
                    <option value="WAREHOUSE_VIETNAM">
                      <Translate contentKey="studentexchangeApp.OrderStatus.WAREHOUSE_VIETNAM" />
                    </option>
                    <option value="DELIVERY_REQUIREMENTS">
                      <Translate contentKey="studentexchangeApp.OrderStatus.DELIVERY_REQUIREMENTS" />
                    </option>
                    <option value="DELIVERING_VIETNAM">
                      <Translate contentKey="studentexchangeApp.OrderStatus.DELIVERING_VIETNAM" />
                    </option>
                    <option value="DELIVERED">
                      <Translate contentKey="studentexchangeApp.OrderStatus.DELIVERED" />
                    </option>
                    <option value="CANCELLED">
                      <Translate contentKey="studentexchangeApp.OrderStatus.CANCELLED" />
                    </option>
                    <option value="LOST">
                      <Translate contentKey="studentexchangeApp.OrderStatus.LOST" />
                    </option>
                    <option value="RETURN">
                      <Translate contentKey="studentexchangeApp.OrderStatus.RETURN" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="statusNameLabel" for="statusName">
                    <Translate contentKey="studentexchangeApp.orderCart.statusName">Status Name</Translate>
                  </Label>
                  <AvField id="order-cart-statusName" type="text" name="statusName" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusStyleLabel" for="statusStyle">
                    <Translate contentKey="studentexchangeApp.orderCart.statusStyle">Status Style</Translate>
                  </Label>
                  <AvField id="order-cart-statusStyle" type="text" name="statusStyle" />
                </AvGroup>
                <AvGroup>
                  <Label id="tallyFeeLabel" for="tallyFee">
                    <Translate contentKey="studentexchangeApp.orderCart.tallyFee">Tally Fee</Translate>
                  </Label>
                  <AvField id="order-cart-tallyFee" type="string" className="form-control" name="tallyFee" />
                </AvGroup>
                <AvGroup>
                  <Label id="totalAmountLabel" for="totalAmount">
                    <Translate contentKey="studentexchangeApp.orderCart.totalAmount">Total Amount</Translate>
                  </Label>
                  <AvField id="order-cart-totalAmount" type="string" className="form-control" name="totalAmount" />
                </AvGroup>
                <AvGroup>
                  <Label id="totalAmountNDTLabel" for="totalAmountNDT">
                    <Translate contentKey="studentexchangeApp.orderCart.totalAmountNDT">Total Amount NDT</Translate>
                  </Label>
                  <AvField id="order-cart-totalAmountNDT" type="string" className="form-control" name="totalAmountNDT" />
                </AvGroup>
                <AvGroup>
                  <Label id="totalPaidByCustomerLabel" for="totalPaidByCustomer">
                    <Translate contentKey="studentexchangeApp.orderCart.totalPaidByCustomer">Total Paid By Customer</Translate>
                  </Label>
                  <AvField id="order-cart-totalPaidByCustomer" type="string" className="form-control" name="totalPaidByCustomer" />
                </AvGroup>
                <AvGroup>
                  <Label id="serviceFeeLabel" for="serviceFee">
                    <Translate contentKey="studentexchangeApp.orderCart.serviceFee">Service Fee</Translate>
                  </Label>
                  <AvField id="order-cart-serviceFee" type="string" className="form-control" name="serviceFee" />
                </AvGroup>
                <AvGroup>
                  <Label id="serviceFeeDiscountLabel" for="serviceFeeDiscount">
                    <Translate contentKey="studentexchangeApp.orderCart.serviceFeeDiscount">Service Fee Discount</Translate>
                  </Label>
                  <AvField id="order-cart-serviceFeeDiscount" type="string" className="form-control" name="serviceFeeDiscount" />
                </AvGroup>
                <AvGroup>
                  <Label id="totalServiceFeeLabel" for="totalServiceFee">
                    <Translate contentKey="studentexchangeApp.orderCart.totalServiceFee">Total Service Fee</Translate>
                  </Label>
                  <AvField id="order-cart-totalServiceFee" type="string" className="form-control" name="totalServiceFee" />
                </AvGroup>
                <AvGroup>
                  <Label id="finalAmountLabel" for="finalAmount">
                    <Translate contentKey="studentexchangeApp.orderCart.finalAmount">Final Amount</Translate>
                  </Label>
                  <AvField id="order-cart-finalAmount" type="string" className="form-control" name="finalAmount" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderNameLabel" for="orderName">
                    <Translate contentKey="studentexchangeApp.orderCart.orderName">Order Name</Translate>
                  </Label>
                  <AvField id="order-cart-orderName" type="text" name="orderName" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderAddressLabel" for="orderAddress">
                    <Translate contentKey="studentexchangeApp.orderCart.orderAddress">Order Address</Translate>
                  </Label>
                  <AvField id="order-cart-orderAddress" type="text" name="orderAddress" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderMobileLabel" for="orderMobile">
                    <Translate contentKey="studentexchangeApp.orderCart.orderMobile">Order Mobile</Translate>
                  </Label>
                  <AvField id="order-cart-orderMobile" type="text" name="orderMobile" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderNoteLabel" for="orderNote">
                    <Translate contentKey="studentexchangeApp.orderCart.orderNote">Order Note</Translate>
                  </Label>
                  <AvField id="order-cart-orderNote" type="text" name="orderNote" />
                </AvGroup>
                <AvGroup>
                  <Label id="createAtLabel" for="createAt">
                    <Translate contentKey="studentexchangeApp.orderCart.createAt">Create At</Translate>
                  </Label>
                  <AvInput
                    id="order-cart-createAt"
                    type="datetime-local"
                    className="form-control"
                    name="createAt"
                    value={isNew ? null : convertDateTimeFromServer(this.props.orderCartEntity.createAt)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="updateAtLabel" for="updateAt">
                    <Translate contentKey="studentexchangeApp.orderCart.updateAt">Update At</Translate>
                  </Label>
                  <AvInput
                    id="order-cart-updateAt"
                    type="datetime-local"
                    className="form-control"
                    name="updateAt"
                    value={isNew ? null : convertDateTimeFromServer(this.props.orderCartEntity.updateAt)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="buyer.login">
                    <Translate contentKey="studentexchangeApp.orderCart.buyer">Buyer</Translate>
                  </Label>
                  <AvInput id="order-cart-buyer" type="select" className="form-control" name="buyerId">
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.login}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="chinaStocker.login">
                    <Translate contentKey="studentexchangeApp.orderCart.chinaStocker">China Stocker</Translate>
                  </Label>
                  <AvInput id="order-cart-chinaStocker" type="select" className="form-control" name="chinaStockerId">
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.login}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="vietnamStocker.login">
                    <Translate contentKey="studentexchangeApp.orderCart.vietnamStocker">Vietnam Stocker</Translate>
                  </Label>
                  <AvInput id="order-cart-vietnamStocker" type="select" className="form-control" name="vietnamStockerId">
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.login}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="exporter.login">
                    <Translate contentKey="studentexchangeApp.orderCart.exporter">Exporter</Translate>
                  </Label>
                  <AvInput id="order-cart-exporter" type="select" className="form-control" name="exporterId">
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.login}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="createBy.login">
                    <Translate contentKey="studentexchangeApp.orderCart.createBy">Create By</Translate>
                  </Label>
                  <AvInput id="order-cart-createBy" type="select" className="form-control" name="createById">
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.login}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="updateBy.login">
                    <Translate contentKey="studentexchangeApp.orderCart.updateBy">Update By</Translate>
                  </Label>
                  <AvInput id="order-cart-updateBy" type="select" className="form-control" name="updateById">
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.login}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/order-cart" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  users: storeState.userManagement.users,
  orderCartEntity: storeState.orderCart.entity,
  loading: storeState.orderCart.loading,
  updating: storeState.orderCart.updating,
  updateSuccess: storeState.orderCart.updateSuccess
});

const mapDispatchToProps = {
  getUsers,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OrderCartUpdate);
