import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IShoppingCart } from 'app/shared/model/shopping-cart.model';
import { getEntities as getShoppingCarts } from 'app/entities/shopping-cart/shopping-cart.reducer';
import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntity, updateEntity, createEntity, reset } from './shopping-cart-item.reducer';
import { IShoppingCartItem } from 'app/shared/model/shopping-cart-item.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IShoppingCartItemUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IShoppingCartItemUpdateState {
  isNew: boolean;
  shoppingCartId: string;
  createById: string;
  updateById: string;
}

export class ShoppingCartItemUpdate extends React.Component<IShoppingCartItemUpdateProps, IShoppingCartItemUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      shoppingCartId: '0',
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

    this.props.getShoppingCarts();
    this.props.getUsers();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { shoppingCartItemEntity } = this.props;
      const entity = {
        ...shoppingCartItemEntity,
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
    this.props.history.push('/entity/shopping-cart-item');
  };

  render() {
    const { shoppingCartItemEntity, shoppingCarts, users, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="studentexchangeApp.shoppingCartItem.home.createOrEditLabel">
              <Translate contentKey="studentexchangeApp.shoppingCartItem.home.createOrEditLabel">
                Create or edit a ShoppingCartItem
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : shoppingCartItemEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="shopping-cart-item-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="itemIdLabel" for="itemId">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.itemId">Item Id</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-itemId" type="text" name="itemId" />
                </AvGroup>
                <AvGroup>
                  <Label id="itemImageLabel" for="itemImage">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.itemImage">Item Image</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-itemImage" type="text" name="itemImage" />
                </AvGroup>
                <AvGroup>
                  <Label id="itemLinkLabel" for="itemLink">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.itemLink">Item Link</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-itemLink" type="text" name="itemLink" />
                </AvGroup>
                <AvGroup>
                  <Label id="itemPriceLabel" for="itemPrice">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.itemPrice">Item Price</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-itemPrice" type="string" className="form-control" name="itemPrice" />
                </AvGroup>
                <AvGroup>
                  <Label id="itemPriceNDTLabel" for="itemPriceNDT">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.itemPriceNDT">Item Price NDT</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-itemPriceNDT" type="string" className="form-control" name="itemPriceNDT" />
                </AvGroup>
                <AvGroup>
                  <Label id="itemNoteLabel" for="itemNote">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.itemNote">Item Note</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-itemNote" type="text" name="itemNote" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertiesIdLabel" for="propertiesId">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.propertiesId">Properties Id</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-propertiesId" type="text" name="propertiesId" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertiesImageLabel" for="propertiesImage">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.propertiesImage">Properties Image</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-propertiesImage" type="text" name="propertiesImage" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertiesMD5Label" for="propertiesMD5">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.propertiesMD5">Properties MD 5</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-propertiesMD5" type="text" name="propertiesMD5" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertiesNameLabel" for="propertiesName">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.propertiesName">Properties Name</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-propertiesName" type="text" name="propertiesName" />
                </AvGroup>
                <AvGroup>
                  <Label id="propertiesTypeLabel" for="propertiesType">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.propertiesType">Properties Type</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-propertiesType" type="text" name="propertiesType" />
                </AvGroup>
                <AvGroup>
                  <Label id="quantityLabel" for="quantity">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.quantity">Quantity</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-quantity" type="string" className="form-control" name="quantity" />
                </AvGroup>
                <AvGroup>
                  <Label id="requireMinLabel" for="requireMin">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.requireMin">Require Min</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-requireMin" type="string" className="form-control" name="requireMin" />
                </AvGroup>
                <AvGroup>
                  <Label id="totalAmountLabel" for="totalAmount">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.totalAmount">Total Amount</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-totalAmount" type="string" className="form-control" name="totalAmount" />
                </AvGroup>
                <AvGroup>
                  <Label id="totalAmountNDTLabel" for="totalAmountNDT">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.totalAmountNDT">Total Amount NDT</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-totalAmountNDT" type="string" className="form-control" name="totalAmountNDT" />
                </AvGroup>
                <AvGroup>
                  <Label id="createAtLabel" for="createAt">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.createAt">Create At</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-createAt" type="date" className="form-control" name="createAt" />
                </AvGroup>
                <AvGroup>
                  <Label id="updateAtLabel" for="updateAt">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.updateAt">Update At</Translate>
                  </Label>
                  <AvField id="shopping-cart-item-updateAt" type="date" className="form-control" name="updateAt" />
                </AvGroup>
                <AvGroup>
                  <Label for="shoppingCart.id">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.shoppingCart">Shopping Cart</Translate>
                  </Label>
                  <AvInput id="shopping-cart-item-shoppingCart" type="select" className="form-control" name="shoppingCartId">
                    <option value="" key="0" />
                    {shoppingCarts
                      ? shoppingCarts.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="createBy.login">
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.createBy">Create By</Translate>
                  </Label>
                  <AvInput id="shopping-cart-item-createBy" type="select" className="form-control" name="createById">
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
                    <Translate contentKey="studentexchangeApp.shoppingCartItem.updateBy">Update By</Translate>
                  </Label>
                  <AvInput id="shopping-cart-item-updateBy" type="select" className="form-control" name="updateById">
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
                <Button tag={Link} id="cancel-save" to="/entity/shopping-cart-item" replace color="info">
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
  shoppingCarts: storeState.shoppingCart.entities,
  users: storeState.userManagement.users,
  shoppingCartItemEntity: storeState.shoppingCartItem.entity,
  loading: storeState.shoppingCartItem.loading,
  updating: storeState.shoppingCartItem.updating,
  updateSuccess: storeState.shoppingCartItem.updateSuccess
});

const mapDispatchToProps = {
  getShoppingCarts,
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
)(ShoppingCartItemUpdate);
