import React from 'react';
import { connect } from 'react-redux';
import qs from 'query-string';

import { Select } from 'antd';
const Option = Select.Option;

import { getAllEntities as getCities } from 'app/entities/city/city.reducer';
import { queryString, stringToSlug } from 'app/shared/util/utils';

export interface ISearchBoxProp extends StateProps, DispatchProps {
  location: any;
  history: any;
  to: string;
}

export interface ISearchBoxState {
  parameters: any;
}

export class SearchBox extends React.Component<ISearchBoxProp> {
  state: ISearchBoxState = {
    parameters: {}
  };

  componentDidMount() {
    if (this.props.location) {
      const parsed = qs.parse(this.props.location.search);
      if (parsed) {
        /* tslint:disable-next-line */
        this.setState({
          parameters: parsed
        });
      }
    }
  }

  onChangeCode = e => {
    const parameters = { code: e.target.value };
    const nextParameter = { ...this.state.parameters, ...parameters };
    this.setState({
      parameters: nextParameter
    });
  };

  onChangeShippingChineCode = e => {
    const parameters = { shippingChineCode: e.target.value };
    const nextParameter = { ...this.state.parameters, ...parameters };
    this.setState({
      parameters: nextParameter
    });
  };

  onChangeMobile = e => {
    const parameters = { createByLogin: e.target.value };
    const nextParameter = { ...this.state.parameters, ...parameters };
    this.setState({
      parameters: nextParameter
    });
  };

  selectOrderStatus = value => {
    const parameters = { status: value };
    const nextParameter = { ...this.state.parameters, ...parameters };
    this.setState({
      parameters: nextParameter
    });
  };

  searchClick = () => {
    this.props.history.push(`${this.props.to}?${queryString(this.state.parameters)}`);
  };

  render() {
    return (
      <div className="ibox-content m-b-sm border-bottom">
        <div className="row">
          <div className="col-sm-4">
            <div className="form-group">
              <label className="control-label" htmlFor="order_id">
                Mã đơn hàng
              </label>
              <input type="text" placeholder="Mã đơn hàng" className="form-control" onChange={this.onChangeCode} />
            </div>
          </div>
          <div className="col-sm-4">
            <div className="form-group">
              <label className="control-label" htmlFor="order_id">
                Mã vận đơn bên Trung Quốc
              </label>
              <input type="text" placeholder="Mã vận đơn" className="form-control" onChange={this.onChangeShippingChineCode} />
            </div>
          </div>
          <div className="col-sm-4">
            <div className="form-group">
              <label className="control-label" htmlFor="status">
                Trạng thái đơn hàng
              </label>
              <Select className="btn-block" onChange={this.selectOrderStatus}>
                <Option value="DEPOSITED">Đã đặt cọc</Option>
                <Option value="ARE_BUYING">Đang mua hàng</Option>
                <Option value="PURCHASED">Đã mua hàng</Option>
                <Option value="SELLER_DELIVERY">Người bán chuyển hàng</Option>
              </Select>
            </div>
          </div>
        </div>
        <div className="row">
          <div className="col-sm-4">
            <div className="form-group">
              <label className="control-label" htmlFor="order_id">
                Số điện thoại khách hàng
              </label>
              <input type="text" placeholder="Số điện thoại" className="form-control" onChange={this.onChangeMobile} />
            </div>
          </div>
          <div className="col-sm-4">
            <div className="form-group">
              <label className="control-label" htmlFor="status" />
              <button className="btn btn-primary btn-block order-search" onClick={this.searchClick}>
                <i className="fa fa-search" /> Tìm kiếm
              </button>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated,
  cities: storeState.city.entities
});

const mapDispatchToProps = { getCities };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SearchBox);
