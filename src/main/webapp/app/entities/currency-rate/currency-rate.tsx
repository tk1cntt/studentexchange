import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './currency-rate.reducer';
import { ICurrencyRate } from 'app/shared/model/currency-rate.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICurrencyRateProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class CurrencyRate extends React.Component<ICurrencyRateProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { currencyRateList, match } = this.props;
    return (
      <div>
        <h2 id="currency-rate-heading">
          <Translate contentKey="studentexchangeApp.currencyRate.home.title">Currency Rates</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="studentexchangeApp.currencyRate.home.createLabel">Create new Currency Rate</Translate>
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
                  <Translate contentKey="studentexchangeApp.currencyRate.currency">Currency</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.currencyRate.rate">Rate</Translate>
                </th>
                <th>
                  <Translate contentKey="studentexchangeApp.currencyRate.createAt">Create At</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {currencyRateList.map((currencyRate, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${currencyRate.id}`} color="link" size="sm">
                      {currencyRate.id}
                    </Button>
                  </td>
                  <td>
                    <Translate contentKey={`studentexchangeApp.CurrencyType.${currencyRate.currency}`} />
                  </td>
                  <td>{currencyRate.rate}</td>
                  <td>
                    <TextFormat type="date" value={currencyRate.createAt} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${currencyRate.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${currencyRate.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${currencyRate.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ currencyRate }: IRootState) => ({
  currencyRateList: currencyRate.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CurrencyRate);
