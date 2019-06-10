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
              <span id="avatar">
                <Translate contentKey="studentexchangeApp.orderItem.avatar">Avatar</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.avatar}</dd>
            <dt>
              <span id="originLink">
                <Translate contentKey="studentexchangeApp.orderItem.originLink">Origin Link</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.originLink}</dd>
            <dt>
              <span id="name">
                <Translate contentKey="studentexchangeApp.orderItem.name">Name</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.name}</dd>
            <dt>
              <span id="note">
                <Translate contentKey="studentexchangeApp.orderItem.note">Note</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.note}</dd>
            <dt>
              <span id="price">
                <Translate contentKey="studentexchangeApp.orderItem.price">Price</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.price}</dd>
            <dt>
              <span id="priceNDT">
                <Translate contentKey="studentexchangeApp.orderItem.priceNDT">Price NDT</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.priceNDT}</dd>
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
              <span id="quantityOrder">
                <Translate contentKey="studentexchangeApp.orderItem.quantityOrder">Quantity Order</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.quantityOrder}</dd>
            <dt>
              <span id="quantityPending">
                <Translate contentKey="studentexchangeApp.orderItem.quantityPending">Quantity Pending</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.quantityPending}</dd>
            <dt>
              <span id="quantityReceived">
                <Translate contentKey="studentexchangeApp.orderItem.quantityReceived">Quantity Received</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.quantityReceived}</dd>
            <dt>
              <span id="totalPrice">
                <Translate contentKey="studentexchangeApp.orderItem.totalPrice">Total Price</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.totalPrice}</dd>
            <dt>
              <span id="totalPriceNDT">
                <Translate contentKey="studentexchangeApp.orderItem.totalPriceNDT">Total Price NDT</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.totalPriceNDT}</dd>
            <dt>
              <span id="website">
                <Translate contentKey="studentexchangeApp.orderItem.website">Website</Translate>
              </span>
            </dt>
            <dd>{orderItemEntity.website}</dd>
            <dt>
              <span id="createAt">
                <Translate contentKey="studentexchangeApp.orderItem.createAt">Create At</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={orderItemEntity.createAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updateAt">
                <Translate contentKey="studentexchangeApp.orderItem.updateAt">Update At</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={orderItemEntity.updateAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
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
