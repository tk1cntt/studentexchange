import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './currency-rate.reducer';
import { ICurrencyRate } from 'app/shared/model/currency-rate.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICurrencyRateUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICurrencyRateUpdateState {
  isNew: boolean;
}

export class CurrencyRateUpdate extends React.Component<ICurrencyRateUpdateProps, ICurrencyRateUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
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
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { currencyRateEntity } = this.props;
      const entity = {
        ...currencyRateEntity,
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
    this.props.history.push('/entity/currency-rate');
  };

  render() {
    const { currencyRateEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="studentexchangeApp.currencyRate.home.createOrEditLabel">
              <Translate contentKey="studentexchangeApp.currencyRate.home.createOrEditLabel">Create or edit a CurrencyRate</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : currencyRateEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="currency-rate-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="currencyLabel">
                    <Translate contentKey="studentexchangeApp.currencyRate.currency">Currency</Translate>
                  </Label>
                  <AvInput
                    id="currency-rate-currency"
                    type="select"
                    className="form-control"
                    name="currency"
                    value={(!isNew && currencyRateEntity.currency) || 'USD'}
                  >
                    <option value="USD">
                      <Translate contentKey="studentexchangeApp.CurrencyType.USD" />
                    </option>
                    <option value="EUR">
                      <Translate contentKey="studentexchangeApp.CurrencyType.EUR" />
                    </option>
                    <option value="CNY">
                      <Translate contentKey="studentexchangeApp.CurrencyType.CNY" />
                    </option>
                    <option value="JPY">
                      <Translate contentKey="studentexchangeApp.CurrencyType.JPY" />
                    </option>
                    <option value="KPW">
                      <Translate contentKey="studentexchangeApp.CurrencyType.KPW" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="rateLabel" for="rate">
                    <Translate contentKey="studentexchangeApp.currencyRate.rate">Rate</Translate>
                  </Label>
                  <AvField id="currency-rate-rate" type="string" className="form-control" name="rate" />
                </AvGroup>
                <AvGroup>
                  <Label id="createAtLabel" for="createAt">
                    <Translate contentKey="studentexchangeApp.currencyRate.createAt">Create At</Translate>
                  </Label>
                  <AvField id="currency-rate-createAt" type="date" className="form-control" name="createAt" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/currency-rate" replace color="info">
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
  currencyRateEntity: storeState.currencyRate.entity,
  loading: storeState.currencyRate.loading,
  updating: storeState.currencyRate.updating,
  updateSuccess: storeState.currencyRate.updateSuccess
});

const mapDispatchToProps = {
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
)(CurrencyRateUpdate);
