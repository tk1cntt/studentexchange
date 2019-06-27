import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './currency-rate.reducer';
import { ICurrencyRate } from 'app/shared/model/currency-rate.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICurrencyRateDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CurrencyRateDetail extends React.Component<ICurrencyRateDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { currencyRateEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="studentexchangeApp.currencyRate.detail.title">CurrencyRate</Translate> [<b>{currencyRateEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="currency">
                <Translate contentKey="studentexchangeApp.currencyRate.currency">Currency</Translate>
              </span>
            </dt>
            <dd>{currencyRateEntity.currency}</dd>
            <dt>
              <span id="rate">
                <Translate contentKey="studentexchangeApp.currencyRate.rate">Rate</Translate>
              </span>
            </dt>
            <dd>{currencyRateEntity.rate}</dd>
            <dt>
              <span id="createAt">
                <Translate contentKey="studentexchangeApp.currencyRate.createAt">Create At</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={currencyRateEntity.createAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/currency-rate" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/currency-rate/${currencyRateEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ currencyRate }: IRootState) => ({
  currencyRateEntity: currencyRate.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CurrencyRateDetail);
