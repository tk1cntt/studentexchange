import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './order-item.reducer';
import { IOrderItem } from 'app/shared/model/order-item.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrderItemDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class OrderItemDetail extends React.Component<IOrderItemDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { orderItemEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="studentexchangeApp.orderItem.detail.title">OrderItem</Translate> [<b>{orderItemEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="itemId">
                <Translate contentKey="studentexchangeApp.orderItem.itemId">Item Id</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.itemId}</dd>
            <dt>
              <span id="itemImage">
                <Translate contentKey="studentexchangeApp.orderItem.itemImage">Item Image</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.itemImage}</dd>
            <dt>
              <span id="itemName">
                <Translate contentKey="studentexchangeApp.orderItem.itemName">Item Name</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.itemName}</dd>
            <dt>
              <span id="itemLink">
                <Translate contentKey="studentexchangeApp.orderItem.itemLink">Item Link</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.itemLink}</dd>
            <dt>
              <span id="itemPrice">
                <Translate contentKey="studentexchangeApp.orderItem.itemPrice">Item Price</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.itemPrice}</dd>
            <dt>
              <span id="itemPriceNDT">
                <Translate contentKey="studentexchangeApp.orderItem.itemPriceNDT">Item Price NDT</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.itemPriceNDT}</dd>
            <dt>
              <span id="itemNote">
                <Translate contentKey="studentexchangeApp.orderItem.itemNote">Item Note</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.itemNote}</dd>
            <dt>
              <span id="propertiesId">
                <Translate contentKey="studentexchangeApp.orderItem.propertiesId">Properties Id</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.propertiesId}</dd>
            <dt>
              <span id="propertiesImage">
                <Translate contentKey="studentexchangeApp.orderItem.propertiesImage">Properties Image</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.propertiesImage}</dd>
            <dt>
              <span id="propertiesMD5">
                <Translate contentKey="studentexchangeApp.orderItem.propertiesMD5">Properties MD 5</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.propertiesMD5}</dd>
            <dt>
              <span id="propertiesName">
                <Translate contentKey="studentexchangeApp.orderItem.propertiesName">Properties Name</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.propertiesName}</dd>
            <dt>
              <span id="propertiesType">
                <Translate contentKey="studentexchangeApp.orderItem.propertiesType">Properties Type</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.propertiesType}</dd>
            <dt>
              <span id="quantity">
                <Translate contentKey="studentexchangeApp.orderItem.quantity">Quantity</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.quantity}</dd>
            <dt>
              <span id="requireMin">
                <Translate contentKey="studentexchangeApp.orderItem.requireMin">Require Min</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.requireMin}</dd>
            <dt>
              <span id="totalAmount">
                <Translate contentKey="studentexchangeApp.orderItem.totalAmount">Total Amount</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.totalAmount}</dd>
            <dt>
              <span id="totalAmountNDT">
                <Translate contentKey="studentexchangeApp.orderItem.totalAmountNDT">Total Amount NDT</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.totalAmountNDT}</dd>
            <dt>
              <span id="createAt">
                <Translate contentKey="studentexchangeApp.orderItem.createAt">Create At</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={orderItemEntity.createAt} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updateAt">
                <Translate contentKey="studentexchangeApp.orderItem.updateAt">Update At</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={orderItemEntity.updateAt} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="studentexchangeApp.orderItem.orderCart">Order Cart</Translate>
            </dt>
            <dd>{orderItemEntity.orderCartId ? orderItemEntity.orderCartId : ''}</dd>
            <dt>
              <Translate contentKey="studentexchangeApp.orderItem.createBy">Create By</Translate>
            </dt>
            <dd>{orderItemEntity.createByLogin ? orderItemEntity.createByLogin : ''}</dd>
            <dt>
              <Translate contentKey="studentexchangeApp.orderItem.updateBy">Update By</Translate>
            </dt>
            <dd>{orderItemEntity.updateByLogin ? orderItemEntity.updateByLogin : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/order-item" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/order-item/${orderItemEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ orderItem }: IRootState) => ({
  orderItemEntity: orderItem.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OrderItemDetail);
