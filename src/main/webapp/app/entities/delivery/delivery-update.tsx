import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IWarehouse } from 'app/shared/model/warehouse.model';
import { getEntities as getWarehouses } from 'app/entities/warehouse/warehouse.reducer';
import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntity, updateEntity, createEntity, reset } from './delivery.reducer';
import { IDelivery } from 'app/shared/model/delivery.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDeliveryUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IDeliveryUpdateState {
  isNew: boolean;
  warehouseId: string;
  createById: string;
  updateById: string;
}

export class DeliveryUpdate extends React.Component<IDeliveryUpdateProps, IDeliveryUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      warehouseId: '0',
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

    this.props.getWarehouses();
    this.props.getUsers();
  }

  saveEntity = (event, errors, values) => {
    values.exportTime = new Date(values.exportTime);
    values.packedTime = new Date(values.packedTime);
    values.createAt = new Date(values.createAt);
    values.updateAt = new Date(values.updateAt);

    if (errors.length === 0) {
      const { deliveryEntity } = this.props;
      const entity = {
        ...deliveryEntity,
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
    this.props.history.push('/entity/delivery');
  };

  render() {
    const { deliveryEntity, warehouses, users, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="studentexchangeApp.delivery.home.createOrEditLabel">
              <Translate contentKey="studentexchangeApp.delivery.home.createOrEditLabel">Create or edit a Delivery</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : deliveryEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="delivery-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="deliveryMethodLabel" for="deliveryMethod">
                    <Translate contentKey="studentexchangeApp.delivery.deliveryMethod">Delivery Method</Translate>
                  </Label>
                  <AvField id="delivery-deliveryMethod" type="text" name="deliveryMethod" />
                </AvGroup>
                <AvGroup>
                  <Label id="deliveryMethodNameLabel" for="deliveryMethodName">
                    <Translate contentKey="studentexchangeApp.delivery.deliveryMethodName">Delivery Method Name</Translate>
                  </Label>
                  <AvField id="delivery-deliveryMethodName" type="text" name="deliveryMethodName" />
                </AvGroup>
                <AvGroup>
                  <Label id="exportTimeLabel" for="exportTime">
                    <Translate contentKey="studentexchangeApp.delivery.exportTime">Export Time</Translate>
                  </Label>
                  <AvInput
                    id="delivery-exportTime"
                    type="datetime-local"
                    className="form-control"
                    name="exportTime"
                    value={isNew ? null : convertDateTimeFromServer(this.props.deliveryEntity.exportTime)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="packedTimeLabel" for="packedTime">
                    <Translate contentKey="studentexchangeApp.delivery.packedTime">Packed Time</Translate>
                  </Label>
                  <AvInput
                    id="delivery-packedTime"
                    type="datetime-local"
                    className="form-control"
                    name="packedTime"
                    value={isNew ? null : convertDateTimeFromServer(this.props.deliveryEntity.packedTime)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="status">
                    <Translate contentKey="studentexchangeApp.delivery.status">Status</Translate>
                  </Label>
                  <AvField id="delivery-status" type="text" name="status" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusNameLabel" for="statusName">
                    <Translate contentKey="studentexchangeApp.delivery.statusName">Status Name</Translate>
                  </Label>
                  <AvField id="delivery-statusName" type="text" name="statusName" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusStyleLabel" for="statusStyle">
                    <Translate contentKey="studentexchangeApp.delivery.statusStyle">Status Style</Translate>
                  </Label>
                  <AvField id="delivery-statusStyle" type="text" name="statusStyle" />
                </AvGroup>
                <AvGroup>
                  <Label id="totalWeightLabel" for="totalWeight">
                    <Translate contentKey="studentexchangeApp.delivery.totalWeight">Total Weight</Translate>
                  </Label>
                  <AvField id="delivery-totalWeight" type="string" className="form-control" name="totalWeight" />
                </AvGroup>
                <AvGroup>
                  <Label id="createAtLabel" for="createAt">
                    <Translate contentKey="studentexchangeApp.delivery.createAt">Create At</Translate>
                  </Label>
                  <AvInput
                    id="delivery-createAt"
                    type="datetime-local"
                    className="form-control"
                    name="createAt"
                    value={isNew ? null : convertDateTimeFromServer(this.props.deliveryEntity.createAt)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="updateAtLabel" for="updateAt">
                    <Translate contentKey="studentexchangeApp.delivery.updateAt">Update At</Translate>
                  </Label>
                  <AvInput
                    id="delivery-updateAt"
                    type="datetime-local"
                    className="form-control"
                    name="updateAt"
                    value={isNew ? null : convertDateTimeFromServer(this.props.deliveryEntity.updateAt)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="warehouse.id">
                    <Translate contentKey="studentexchangeApp.delivery.warehouse">Warehouse</Translate>
                  </Label>
                  <AvInput id="delivery-warehouse" type="select" className="form-control" name="warehouseId">
                    <option value="" key="0" />
                    {warehouses
                      ? warehouses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="createBy.login">
                    <Translate contentKey="studentexchangeApp.delivery.createBy">Create By</Translate>
                  </Label>
                  <AvInput id="delivery-createBy" type="select" className="form-control" name="createById">
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
                    <Translate contentKey="studentexchangeApp.delivery.updateBy">Update By</Translate>
                  </Label>
                  <AvInput id="delivery-updateBy" type="select" className="form-control" name="updateById">
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
                <Button tag={Link} id="cancel-save" to="/entity/delivery" replace color="info">
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
  warehouses: storeState.warehouse.entities,
  users: storeState.userManagement.users,
  deliveryEntity: storeState.delivery.entity,
  loading: storeState.delivery.loading,
  updating: storeState.delivery.updating,
  updateSuccess: storeState.delivery.updateSuccess
});

const mapDispatchToProps = {
  getWarehouses,
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
)(DeliveryUpdate);
