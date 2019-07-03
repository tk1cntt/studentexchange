import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './shopping-cart.reducer';
import { IShoppingCart } from 'app/shared/model/shopping-cart.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IShoppingCartDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ShoppingCartDetail extends React.Component<IShoppingCartDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { shoppingCartEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="studentexchangeApp.shoppingCart.detail.title">ShoppingCart</Translate> [<b>{shoppingCartEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="aliwangwang">
                <Translate contentKey="studentexchangeApp.shoppingCart.aliwangwang">Aliwangwang</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.aliwangwang}</dd>
            <dt>
              <span id="depositAmount">
                <Translate contentKey="studentexchangeApp.shoppingCart.depositAmount">Deposit Amount</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.depositAmount}</dd>
            <dt>
              <span id="depositRatio">
                <Translate contentKey="studentexchangeApp.shoppingCart.depositRatio">Deposit Ratio</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.depositRatio}</dd>
            <dt>
              <span id="serviceFee">
                <Translate contentKey="studentexchangeApp.shoppingCart.serviceFee">Service Fee</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.serviceFee}</dd>
            <dt>
              <span id="serviceFeeDiscount">
                <Translate contentKey="studentexchangeApp.shoppingCart.serviceFeeDiscount">Service Fee Discount</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.serviceFeeDiscount}</dd>
            <dt>
              <span id="itemChecking">
                <Translate contentKey="studentexchangeApp.shoppingCart.itemChecking">Item Checking</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.itemChecking ? 'true' : 'false'}</dd>
            <dt>
              <span id="itemWoodCrating">
                <Translate contentKey="studentexchangeApp.shoppingCart.itemWoodCrating">Item Wood Crating</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.itemWoodCrating ? 'true' : 'false'}</dd>
            <dt>
              <span id="shopId">
                <Translate contentKey="studentexchangeApp.shoppingCart.shopId">Shop Id</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.shopId}</dd>
            <dt>
              <span id="shopLink">
                <Translate contentKey="studentexchangeApp.shoppingCart.shopLink">Shop Link</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.shopLink}</dd>
            <dt>
              <span id="shopName">
                <Translate contentKey="studentexchangeApp.shoppingCart.shopName">Shop Name</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.shopName}</dd>
            <dt>
              <span id="shopNote">
                <Translate contentKey="studentexchangeApp.shoppingCart.shopNote">Shop Note</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.shopNote}</dd>
            <dt>
              <span id="sourceData">
                <Translate contentKey="studentexchangeApp.shoppingCart.sourceData">Source Data</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.sourceData}</dd>
            <dt>
              <span id="tallyFee">
                <Translate contentKey="studentexchangeApp.shoppingCart.tallyFee">Tally Fee</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.tallyFee}</dd>
            <dt>
              <span id="totalAmount">
                <Translate contentKey="studentexchangeApp.shoppingCart.totalAmount">Total Amount</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.totalAmount}</dd>
            <dt>
              <span id="totalLink">
                <Translate contentKey="studentexchangeApp.shoppingCart.totalLink">Total Link</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.totalLink}</dd>
            <dt>
              <span id="totalQuantity">
                <Translate contentKey="studentexchangeApp.shoppingCart.totalQuantity">Total Quantity</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.totalQuantity}</dd>
            <dt>
              <span id="finalAmount">
                <Translate contentKey="studentexchangeApp.shoppingCart.finalAmount">Final Amount</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.finalAmount}</dd>
            <dt>
              <span id="website">
                <Translate contentKey="studentexchangeApp.shoppingCart.website">Website</Translate>
              </span>
            </dt>
            <dd>{shoppingCartEntity.website}</dd>
            <dt>
              <span id="createAt">
                <Translate contentKey="studentexchangeApp.shoppingCart.createAt">Create At</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={shoppingCartEntity.createAt} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updateAt">
                <Translate contentKey="studentexchangeApp.shoppingCart.updateAt">Update At</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={shoppingCartEntity.updateAt} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="studentexchangeApp.shoppingCart.createBy">Create By</Translate>
            </dt>
            <dd>{shoppingCartEntity.createByLogin ? shoppingCartEntity.createByLogin : ''}</dd>
            <dt>
              <Translate contentKey="studentexchangeApp.shoppingCart.updateBy">Update By</Translate>
            </dt>
            <dd>{shoppingCartEntity.updateByLogin ? shoppingCartEntity.updateByLogin : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/shopping-cart" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/shopping-cart/${shoppingCartEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ shoppingCart }: IRootState) => ({
  shoppingCartEntity: shoppingCart.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ShoppingCartDetail);
