import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './order-item.reducer';
import { IOrderItem } from 'app/shared/model/order-item.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrderItemProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class OrderItem extends React.Component<IOrderItemProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { orderItemList, match } = this.props;
    return (
      <div>
        <h2 id="order-item-heading">
          <Translate contentKey="studentexchangeApp.orderItem.home.title">Order Items</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="studentexchangeApp.orderItem.home.createLabel">Create new Order Item</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.itemId">Item Id</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.itemImage">Item Image</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.itemName">Item Name</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.itemLink">Item Link</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.itemPrice">Item Price</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.itemPriceNDT">Item Price NDT</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.itemNote">Item Note</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.propertiesId">Properties Id</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.propertiesImage">Properties Image</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.propertiesMD5">Properties MD 5</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.propertiesName">Properties Name</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.propertiesType">Properties Type</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.quantity">Quantity</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.requireMin">Require Min</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.totalAmount">Total Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.totalAmountNDT">Total Amount NDT</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.createAt">Create At</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.updateAt">Update At</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.orderCart">Order Cart</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.createBy">Create By</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.orderItem.updateBy">Update By</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {orderItemList.map((orderItem, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${orderItem.id}`} color="link" size="sm">
                      {orderItem.id}
                    </Button>
                  </td>
                  <td>{orderItem.itemId}</td>
                  <td>{orderItem.itemImage}</td>
                  <td>{orderItem.itemName}</td>
                  <td>{orderItem.itemLink}</td>
                  <td>{orderItem.itemPrice}</td>
                  <td>{orderItem.itemPriceNDT}</td>
                  <td>{orderItem.itemNote}</td>
                  <td>{orderItem.propertiesId}</td>
                  <td>{orderItem.propertiesImage}</td>
                  <td>{orderItem.propertiesMD5}</td>
                  <td>{orderItem.propertiesName}</td>
                  <td>{orderItem.propertiesType}</td>
                  <td>{orderItem.quantity}</td>
                  <td>{orderItem.requireMin}</td>
                  <td>{orderItem.totalAmount}</td>
                  <td>{orderItem.totalAmountNDT}</td>
                  <td>
                    <TextFormat type="date" value={orderItem.createAt} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={orderItem.updateAt} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{orderItem.orderCartId ? <Link to={`order-cart/${orderItem.orderCartId}`}>{orderItem.orderCartId}</Link> : ''}</td>
                  <td>{orderItem.createByLogin ? orderItem.createByLogin : ''}</td>
                  <td>{orderItem.updateByLogin ? orderItem.updateByLogin : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${orderItem.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${orderItem.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${orderItem.id}/delete`} color="danger" size="sm">
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
      </div>
    );
  }
}

const mapStateToProps = ({ orderItem }: IRootState) => ({
  orderItemList: orderItem.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OrderItem);
