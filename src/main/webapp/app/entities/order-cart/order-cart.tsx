import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import {
  Translate,
  ICrudGetAllAction,
  TextFormat,
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './order-cart.reducer';
import { IOrderCart } from 'app/shared/model/order-cart.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IOrderCartProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IOrderCartState = IPaginationBaseState;

export class OrderCart extends React.Component<IOrderCartProps, IOrderCartState> {
  state: IOrderCartState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { orderCartList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="order-cart-heading">
          <Translate contentKey="studentexchangeApp.orderCart.home.title">Order Carts</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="studentexchangeApp.orderCart.home.createLabel">Create new Order Cart</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('code')}>
                  <Translate contentKey="studentexchangeApp.orderCart.code">Code</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('shippingChinaCode')}>
                  <Translate contentKey="studentexchangeApp.orderCart.shippingChinaCode">Shipping China Code</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('avatar')}>
                  <Translate contentKey="studentexchangeApp.orderCart.avatar">Avatar</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('aliwangwang')}>
                  <Translate contentKey="studentexchangeApp.orderCart.aliwangwang">Aliwangwang</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('amountDiscount')}>
                  <Translate contentKey="studentexchangeApp.orderCart.amountDiscount">Amount Discount</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('amountPaid')}>
                  <Translate contentKey="studentexchangeApp.orderCart.amountPaid">Amount Paid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('depositAmount')}>
                  <Translate contentKey="studentexchangeApp.orderCart.depositAmount">Deposit Amount</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('depositRatio')}>
                  <Translate contentKey="studentexchangeApp.orderCart.depositRatio">Deposit Ratio</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('depositTime')}>
                  <Translate contentKey="studentexchangeApp.orderCart.depositTime">Deposit Time</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('domesticShippingChinaFeeNDT')}>
                  <Translate contentKey="studentexchangeApp.orderCart.domesticShippingChinaFeeNDT">
                    Domestic Shipping China Fee NDT
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('domesticShippingChinaFee')}>
                  <Translate contentKey="studentexchangeApp.orderCart.domesticShippingChinaFee">Domestic Shipping China Fee</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('domesticShippingVietnamFee')}>
                  <Translate contentKey="studentexchangeApp.orderCart.domesticShippingVietnamFee">Domestic Shipping Vietnam Fee</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('quantityOrder')}>
                  <Translate contentKey="studentexchangeApp.orderCart.quantityOrder">Quantity Order</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('quantityPending')}>
                  <Translate contentKey="studentexchangeApp.orderCart.quantityPending">Quantity Pending</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('quantityReceived')}>
                  <Translate contentKey="studentexchangeApp.orderCart.quantityReceived">Quantity Received</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('rate')}>
                  <Translate contentKey="studentexchangeApp.orderCart.rate">Rate</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('receiverName')}>
                  <Translate contentKey="studentexchangeApp.orderCart.receiverName">Receiver Name</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('receiverAddress')}>
                  <Translate contentKey="studentexchangeApp.orderCart.receiverAddress">Receiver Address</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('receiverMobile')}>
                  <Translate contentKey="studentexchangeApp.orderCart.receiverMobile">Receiver Mobile</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('receiverNote')}>
                  <Translate contentKey="studentexchangeApp.orderCart.receiverNote">Receiver Note</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('refundAmountByAlipay')}>
                  <Translate contentKey="studentexchangeApp.orderCart.refundAmountByAlipay">Refund Amount By Alipay</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('refundAmountByComplaint')}>
                  <Translate contentKey="studentexchangeApp.orderCart.refundAmountByComplaint">Refund Amount By Complaint</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('refundAmountByOrder')}>
                  <Translate contentKey="studentexchangeApp.orderCart.refundAmountByOrder">Refund Amount By Order</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('refundAmountPending')}>
                  <Translate contentKey="studentexchangeApp.orderCart.refundAmountPending">Refund Amount Pending</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('shippingChinaVietnamFee')}>
                  <Translate contentKey="studentexchangeApp.orderCart.shippingChinaVietnamFee">Shipping China Vietnam Fee</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('shippingChinaVietnamFeeDiscount')}>
                  <Translate contentKey="studentexchangeApp.orderCart.shippingChinaVietnamFeeDiscount">
                    Shipping China Vietnam Fee Discount
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('serviceFee')}>
                  <Translate contentKey="studentexchangeApp.orderCart.serviceFee">Service Fee</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('serviceFeeDiscount')}>
                  <Translate contentKey="studentexchangeApp.orderCart.serviceFeeDiscount">Service Fee Discount</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('itemChecking')}>
                  <Translate contentKey="studentexchangeApp.orderCart.itemChecking">Item Checking</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('itemWoodCrating')}>
                  <Translate contentKey="studentexchangeApp.orderCart.itemWoodCrating">Item Wood Crating</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('shopId')}>
                  <Translate contentKey="studentexchangeApp.orderCart.shopId">Shop Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('shopLink')}>
                  <Translate contentKey="studentexchangeApp.orderCart.shopLink">Shop Link</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('shopName')}>
                  <Translate contentKey="studentexchangeApp.orderCart.shopName">Shop Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('shopNote')}>
                  <Translate contentKey="studentexchangeApp.orderCart.shopNote">Shop Note</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('website')}>
                  <Translate contentKey="studentexchangeApp.orderCart.website">Website</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('status')}>
                  <Translate contentKey="studentexchangeApp.orderCart.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('statusName')}>
                  <Translate contentKey="studentexchangeApp.orderCart.statusName">Status Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('statusStyle')}>
                  <Translate contentKey="studentexchangeApp.orderCart.statusStyle">Status Style</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('tallyFee')}>
                  <Translate contentKey="studentexchangeApp.orderCart.tallyFee">Tally Fee</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('totalAmount')}>
                  <Translate contentKey="studentexchangeApp.orderCart.totalAmount">Total Amount</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('totalAmountNDT')}>
                  <Translate contentKey="studentexchangeApp.orderCart.totalAmountNDT">Total Amount NDT</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('totalAmountChinaNDT')}>
                  <Translate contentKey="studentexchangeApp.orderCart.totalAmountChinaNDT">Total Amount China NDT</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('totalPaidByCustomer')}>
                  <Translate contentKey="studentexchangeApp.orderCart.totalPaidByCustomer">Total Paid By Customer</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('totalServiceFee')}>
                  <Translate contentKey="studentexchangeApp.orderCart.totalServiceFee">Total Service Fee</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('totalQuantity')}>
                  <Translate contentKey="studentexchangeApp.orderCart.totalQuantity">Total Quantity</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('finalAmount')}>
                  <Translate contentKey="studentexchangeApp.orderCart.finalAmount">Final Amount</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('createAt')}>
                  <Translate contentKey="studentexchangeApp.orderCart.createAt">Create At</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('updateAt')}>
                  <Translate contentKey="studentexchangeApp.orderCart.updateAt">Update At</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderCart.buyer">Buyer</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderCart.chinaStocker">China Stocker</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderCart.vietnamStocker">Vietnam Stocker</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderCart.exporter">Exporter</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderCart.createBy">Create By</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderCart.updateBy">Update By</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {orderCartList.map((orderCart, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${orderCart.id}`} color="link" size="sm">
                      {orderCart.id}
                    </Button>
                  </td>
                  <td>{orderCart.code}</td>
                  <td>{orderCart.shippingChinaCode}</td>
                  <td>{orderCart.avatar}</td>
                  <td>{orderCart.aliwangwang}</td>
                  <td>{orderCart.amountDiscount}</td>
                  <td>{orderCart.amountPaid}</td>
                  <td>{orderCart.depositAmount}</td>
                  <td>{orderCart.depositRatio}</td>
                  <td>
                    <TextFormat type="date" value={orderCart.depositTime} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{orderCart.domesticShippingChinaFeeNDT}</td>
                  <td>{orderCart.domesticShippingChinaFee}</td>
                  <td>{orderCart.domesticShippingVietnamFee}</td>
                  <td>{orderCart.quantityOrder}</td>
                  <td>{orderCart.quantityPending}</td>
                  <td>{orderCart.quantityReceived}</td>
                  <td>{orderCart.rate}</td>
                  <td>{orderCart.receiverName}</td>
                  <td>{orderCart.receiverAddress}</td>
                  <td>{orderCart.receiverMobile}</td>
                  <td>{orderCart.receiverNote}</td>
                  <td>{orderCart.refundAmountByAlipay}</td>
                  <td>{orderCart.refundAmountByComplaint}</td>
                  <td>{orderCart.refundAmountByOrder}</td>
                  <td>{orderCart.refundAmountPending}</td>
                  <td>{orderCart.shippingChinaVietnamFee}</td>
                  <td>{orderCart.shippingChinaVietnamFeeDiscount}</td>
                  <td>{orderCart.serviceFee}</td>
                  <td>{orderCart.serviceFeeDiscount}</td>
                  <td>{orderCart.itemChecking ? 'true' : 'false'}</td>
                  <td>{orderCart.itemWoodCrating ? 'true' : 'false'}</td>
                  <td>{orderCart.shopId}</td>
                  <td>{orderCart.shopLink}</td>
                  <td>{orderCart.shopName}</td>
                  <td>{orderCart.shopNote}</td>
                  <td>{orderCart.website}</td>
                  <td>
                    <Translate contentKey={`studentexchangeApp.OrderStatus.${orderCart.status}`} />
                  </td>
                  <td>{orderCart.statusName}</td>
                  <td>{orderCart.statusStyle}</td>
                  <td>{orderCart.tallyFee}</td>
                  <td>{orderCart.totalAmount}</td>
                  <td>{orderCart.totalAmountNDT}</td>
                  <td>{orderCart.totalAmountChinaNDT}</td>
                  <td>{orderCart.totalPaidByCustomer}</td>
                  <td>{orderCart.totalServiceFee}</td>
                  <td>{orderCart.totalQuantity}</td>
                  <td>{orderCart.finalAmount}</td>
                  <td>
                    <TextFormat type="date" value={orderCart.createAt} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={orderCart.updateAt} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{orderCart.buyerLogin ? orderCart.buyerLogin : ''}</td>
                  <td>{orderCart.chinaStockerLogin ? orderCart.chinaStockerLogin : ''}</td>
                  <td>{orderCart.vietnamStockerLogin ? orderCart.vietnamStockerLogin : ''}</td>
                  <td>{orderCart.exporterLogin ? orderCart.exporterLogin : ''}</td>
                  <td>{orderCart.createByLogin ? orderCart.createByLogin : ''}</td>
                  <td>{orderCart.updateByLogin ? orderCart.updateByLogin : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${orderCart.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${orderCart.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${orderCart.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ orderCart }: IRootState) => ({
  orderCartList: orderCart.entities,
  totalItems: orderCart.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OrderCart);
