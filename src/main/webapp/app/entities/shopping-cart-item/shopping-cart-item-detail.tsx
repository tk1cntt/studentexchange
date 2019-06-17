import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './shopping-cart-item.reducer';
import { IShoppingCartItem } from 'app/shared/model/shopping-cart-item.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IShoppingCartItemDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ShoppingCartItemDetail extends React.Component<IShoppingCartItemDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { shoppingCartItemEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="studentexchangeApp.shoppingCartItem.detail.title">ShoppingCartItem</Translate> [
            <b>{shoppingCartItemEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="itemId">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.itemId">Item Id</Translate>
              </span>
            </dt>
            <dd>{shoppingCartItemEntity.itemId}</dd>
            <dt>
              <span id="itemImage">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.itemImage">Item Image</Translate>
              </span>
            </dt>
            <dd>{shoppingCartItemEntity.itemImage}</dd>
            <dt>
              <span id="itemName">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.itemName">Item Name</Translate>
              </span>
            </dt>
            <dd>{shoppingCartItemEntity.itemName}</dd>
            <dt>
              <span id="itemLink">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.itemLink">Item Link</Translate>
              </span>
            </dt>
            <dd>{shoppingCartItemEntity.itemLink}</dd>
            <dt>
              <span id="itemPrice">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.itemPrice">Item Price</Translate>
              </span>
            </dt>
            <dd>{shoppingCartItemEntity.itemPrice}</dd>
            <dt>
              <span id="itemPriceNDT">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.itemPriceNDT">Item Price NDT</Translate>
              </span>
            </dt>
            <dd>{shoppingCartItemEntity.itemPriceNDT}</dd>
            <dt>
              <span id="itemNote">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.itemNote">Item Note</Translate>
              </span>
            </dt>
            <dd>{shoppingCartItemEntity.itemNote}</dd>
            <dt>
              <span id="propertiesId">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.propertiesId">Properties Id</Translate>
              </span>
            </dt>
            <dd>{shoppingCartItemEntity.propertiesId}</dd>
            <dt>
              <span id="propertiesImage">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.propertiesImage">Properties Image</Translate>
              </span>
            </dt>
            <dd>{shoppingCartItemEntity.propertiesImage}</dd>
            <dt>
              <span id="propertiesMD5">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.propertiesMD5">Properties MD 5</Translate>
              </span>
            </dt>
            <dd>{shoppingCartItemEntity.propertiesMD5}</dd>
            <dt>
              <span id="propertiesName">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.propertiesName">Properties Name</Translate>
              </span>
            </dt>
            <dd>{shoppingCartItemEntity.propertiesName}</dd>
            <dt>
              <span id="propertiesType">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.propertiesType">Properties Type</Translate>
              </span>
            </dt>
            <dd>{shoppingCartItemEntity.propertiesType}</dd>
            <dt>
              <span id="quantity">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.quantity">Quantity</Translate>
              </span>
            </dt>
            <dd>{shoppingCartItemEntity.quantity}</dd>
            <dt>
              <span id="requireMin">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.requireMin">Require Min</Translate>
              </span>
            </dt>
            <dd>{shoppingCartItemEntity.requireMin}</dd>
            <dt>
              <span id="totalAmount">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.totalAmount">Total Amount</Translate>
              </span>
            </dt>
            <dd>{shoppingCartItemEntity.totalAmount}</dd>
            <dt>
              <span id="totalAmountNDT">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.totalAmountNDT">Total Amount NDT</Translate>
              </span>
            </dt>
            <dd>{shoppingCartItemEntity.totalAmountNDT}</dd>
            <dt>
              <span id="createAt">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.createAt">Create At</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={shoppingCartItemEntity.createAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updateAt">
                <Translate contentKey="studentexchangeApp.shoppingCartItem.updateAt">Update At</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={shoppingCartItemEntity.updateAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="studentexchangeApp.shoppingCartItem.shoppingCart">Shopping Cart</Translate>
            </dt>
            <dd>{shoppingCartItemEntity.shoppingCartId ? shoppingCartItemEntity.shoppingCartId : ''}</dd>
            <dt>
              <Translate contentKey="studentexchangeApp.shoppingCartItem.createBy">Create By</Translate>
            </dt>
            <dd>{shoppingCartItemEntity.createByLogin ? shoppingCartItemEntity.createByLogin : ''}</dd>
            <dt>
              <Translate contentKey="studentexchangeApp.shoppingCartItem.updateBy">Update By</Translate>
            </dt>
            <dd>{shoppingCartItemEntity.updateByLogin ? shoppingCartItemEntity.updateByLogin : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/shopping-cart-item" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/shopping-cart-item/${shoppingCartItemEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ shoppingCartItem }: IRootState) => ({
  shoppingCartItemEntity: shoppingCartItem.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ShoppingCartItemDetail);
