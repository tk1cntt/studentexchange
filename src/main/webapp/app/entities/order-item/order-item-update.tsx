import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IOrderCart } from 'app/shared/model/order-cart.model';
import { getEntities as getOrderCarts } from 'app/entities/order-cart/order-cart.reducer';
import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntity, updateEntity, createEntity, reset } from './order-item.reducer';
import { IOrderItem } from 'app/shared/model/order-item.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOrderItemUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IOrderItemUpdateState {
  isNew: boolean;
  orderCartId: string;
  createById: string;
  updateById: string;
}

export class OrderItemUpdate extends React.Component<IOrderItemUpdateProps, IOrderItemUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      orderCartId: '0',
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

    this.props.getOrderCarts();
    this.props.getUsers();
  }

  saveEntity = (event, errors, values) => {
    values.createAt = new Date(values.createAt);
    values.updateAt = new Date(values.updateAt);

    if (errors.length === 0) {
      const { orderItemEntity } = this.props;
      const entity = {
        ...orderItemEntity,
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
    this.props.history.push('/entity/order-item');
  };

  render() {
    const { orderItemEntity, orderCarts, users, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="studentexchangeApp.orderItem.home.createOrEditLabel">
              <Translate contentKey="studentexchangeApp.orderItem.home.createOrEditLabel">Create or edit a OrderItem</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : orderItemEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="order-item-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="itemIdLabel" for="itemId">
                    <Translate contentKey="studentexchangeApp.orderItem.itemId">Item Id</Translate>
                  </Label>
                  <AvField id="order-item-itemId" type="text" name="itemId" />
                </AvGroup>
                <AvGroup>
                  <Label id="itemImageLabel" for="itemImage">
                    <Translate contentKey="studentexchangeApp.orderItem.itemImage">Item Image</Translate>
                  </Label>
                  <AvField id="order-item-itemImage" type="text" name="itemImage" />
                </AvGroup>
                <AvGroup>
                  <Label id="itemNameLabel" for="itemName">
                    <Translate contentKey="studentexchangeApp.orderItem.itemName">Item Name</Translate>
                  </Label>
                  <AvField id="order-item-itemName" type="text" name="itemName" />
                </AvGroup>
                <AvGroup>
                  <Label id="itemLinkLabel" for="itemLink">
                    <Translate contentKey="studentexchangeApp.orderItem.itemLink">Item Link</Translate>
                  </Label>
                  <AvField id="order-item-itemLink" type="text" name="itemLink" />
                </AvGroup>
                <AvGroup>
                  <Label id="itemPriceLabel" for="itemPrice">
                    <Translate contentKey="studentexchangeApp.orderItem.itemPrice">Item Price</Translate>
                  </Label>
                  <AvField id="order-item-itemPrice" type="string" className="form-control" name="itemPrice" />
                </AvGroup>
                <AvGroup>
                  <Label id="itemPriceNDTLabel" for="itemPriceNDT">
                    <Translate contentKey="studentexchangeApp.orderItem.itemPriceNDT">Item Price NDT</Translate>
                  </Label>
                  <AvField id="order-item-itemPriceNDT" type="string" className="form-control" name="itemPriceNDT" />
                </AvGroup>
                <AvGroup>
                  <Label id="itemNoteLabel" for="itemNote">
                    <Translate contentKey="studentexchangeApp.orderItem.itemNote">Item Note</Translate>
                  </Label>
                  <AvField id="order-item-itemNote" type="text" name="itemNote" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertiesIdLabel" for="propertiesId">
                    <Translate contentKey="studentexchangeApp.orderItem.propertiesId">Properties Id</Translate>
                  </Label>
                  <AvField id="order-item-propertiesId" type="text" name="propertiesId" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertiesImageLabel" for="propertiesImage">
                    <Translate contentKey="studentexchangeApp.orderItem.propertiesImage">Properties Image</Translate>
                  </Label>
                  <AvField id="order-item-propertiesImage" type="text" name="propertiesImage" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertiesMD5Label" for="propertiesMD5">
                    <Translate contentKey="studentexchangeApp.orderItem.propertiesMD5">Properties MD 5</Translate>
                  </Label>
                  <AvField id="order-item-propertiesMD5" type="text" name="propertiesMD5" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertiesNameLabel" for="propertiesName">
                    <Translate contentKey="studentexchangeApp.orderItem.propertiesName">Properties Name</Translate>
                  </Label>
                  <AvField id="order-item-propertiesName" type="text" name="propertiesName" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertiesTypeLabel" for="propertiesType">
                    <Translate contentKey="studentexchangeApp.orderItem.propertiesType">Properties Type</Translate>
                  </Label>
                  <AvField id="order-item-propertiesType" type="text" name="propertiesType" />
                </AvGroup>
                <AvGroup>
                  <Label id="quantityLabel" for="quantity">
                    <Translate contentKey="studentexchangeApp.orderItem.quantity">Quantity</Translate>
                  </Label>
                  <AvField id="order-item-quantity" type="string" className="form-control" name="quantity" />
                </AvGroup>
                <AvGroup>
                  <Label id="requireMinLabel" for="requireMin">
                    <Translate contentKey="studentexchangeApp.orderItem.requireMin">Require Min</Translate>
                  </Label>
                  <AvField id="order-item-requireMin" type="string" className="form-control" name="requireMin" />
                </AvGroup>
                <AvGroup>
                  <Label id="totalAmountLabel" for="totalAmount">
                    <Translate contentKey="studentexchangeApp.orderItem.totalAmount">Total Amount</Translate>
                  </Label>
                  <AvField id="order-item-totalAmount" type="string" className="form-control" name="totalAmount" />
                </AvGroup>
                <AvGroup>
                  <Label id="totalAmountNDTLabel" for="totalAmountNDT">
                    <Translate contentKey="studentexchangeApp.orderItem.totalAmountNDT">Total Amount NDT</Translate>
                  </Label>
                  <AvField id="order-item-totalAmountNDT" type="string" className="form-control" name="totalAmountNDT" />
                </AvGroup>
                <AvGroup>
                  <Label id="createAtLabel" for="createAt">
                    <Translate contentKey="studentexchangeApp.orderItem.createAt">Create At</Translate>
                  </Label>
                  <AvInput
                    id="order-item-createAt"
                    type="datetime-local"
                    className="form-control"
                    name="createAt"
                    value={isNew ? null : convertDateTimeFromServer(this.props.orderItemEntity.createAt)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="updateAtLabel" for="updateAt">
                    <Translate contentKey="studentexchangeApp.orderItem.updateAt">Update At</Translate>
                  </Label>
                  <AvInput
                    id="order-item-updateAt"
                    type="datetime-local"
                    className="form-control"
                    name="updateAt"
                    value={isNew ? null : convertDateTimeFromServer(this.props.orderItemEntity.updateAt)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="orderCart.id">
                    <Translate contentKey="studentexchangeApp.orderItem.orderCart">Order Cart</Translate>
                  </Label>
                  <AvInput id="order-item-orderCart" type="select" className="form-control" name="orderCartId">
                    <option value="" key="0" />
                    {orderCarts
                      ? orderCarts.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="createBy.login">
                    <Translate contentKey="studentexchangeApp.orderItem.createBy">Create By</Translate>
                  </Label>
                  <AvInput id="order-item-createBy" type="select" className="form-control" name="createById">
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
                    <Translate contentKey="studentexchangeApp.orderItem.updateBy">Update By</Translate>
                  </Label>
                  <AvInput id="order-item-updateBy" type="select" className="form-control" name="updateById">
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
                <Button tag={Link} id="cancel-save" to="/entity/order-item" replace color="info">
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
  orderCarts: storeState.orderCart.entities,
  users: storeState.userManagement.users,
  orderItemEntity: storeState.orderItem.entity,
  loading: storeState.orderItem.loading,
  updating: storeState.orderItem.updating,
  updateSuccess: storeState.orderItem.updateSuccess
});

const mapDispatchToProps = {
  getOrderCarts,
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
)(OrderItemUpdate);
