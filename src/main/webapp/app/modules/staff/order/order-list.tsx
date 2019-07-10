import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import {
  Translate,
  ICrudGetAllAction,
  TextFormat,
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination
} from 'react-jhipster';
import { Select } from 'antd';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { formatCurency, encodeId } from 'app/shared/util/utils';

import Header from 'app/shared/layout/header/header';
import Footer from 'app/shared/layout/footer/footer';
import Sidebar from 'app/shared/layout/sidebar/sidebar';

import { IRootState } from 'app/shared/reducers';
import { getEntities, searchOrder, reset } from 'app/entities/order-cart/order-cart.reducer';
import { IOrderCart } from 'app/shared/model/order-cart.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

const { Option } = Select;

export interface IOrderCartProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IOrderCartState = IPaginationBaseState;

export class OrderCart extends React.Component<IOrderCartProps, IOrderCartState> {
  state: IOrderCartState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  componentWillUnmount() {
    this.props.reset();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=createAt,asc`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.searchOrder('', activePage - 1, itemsPerPage, `createAt,desc`);
    // this.props.getEntities(activePage - 1, itemsPerPage, `createAt,asc`);
  };

  selectOrderStatus = () => {};

  render() {
    const { orderCartList, match, totalItems } = this.props;
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="order-management" activeSubMenu="order-list" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row  border-bottom white-bg dashboard-header">
            <h3>Danh sách đơn hàng</h3>
          </div>
          <div className="wrapper wrapper-content animated fadeInRight ecommerce">
            <div className="ibox-content m-b-sm border-bottom">
              <div className="row">
                <div className="col-sm-4">
                  <div className="form-group">
                    <label className="control-label" htmlFor="order_id">
                      Mã đơn hàng
                    </label>
                    <input type="text" id="order_id" name="order_id" placeholder="Mã đơn hàng" className="form-control" />
                  </div>
                </div>
                <div className="col-sm-4">
                  <div className="form-group">
                    <label className="control-label" htmlFor="order_id">
                      Mã vận đơn bên Trung Quốc
                    </label>
                    <input type="text" id="order_id" name="order_id" placeholder="Mã vận đơn" className="form-control" />
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
                    <input type="text" id="order_id" name="order_id" placeholder="Số điện thoại" className="form-control" />
                  </div>
                </div>
                <div className="col-sm-4">
                  <div className="form-group">
                    <label className="control-label" htmlFor="status" />
                    <button className="btn btn-primary btn-block order-search">
                      <i className="fa fa-search" /> Tìm kiếm
                    </button>
                  </div>
                </div>
              </div>
            </div>
            <div className="row">
              <div className="col-lg-12">
                <div className="ibox">
                  <div className="ibox-content">
                    <table className="footable table table-stripped toggle-arrow-tiny tablet breakpoint footable-loaded">
                      <thead>
                        <tr>
                          <th>Mã đơn hàng</th>
                          <th>Mã vận đơn bên Trung Quốc</th>
                          <th>Khách hàng</th>
                          <th>Tổng tiền</th>
                          <th>Ngày đặt</th>
                          <th>Trạng thái</th>
                          <th />
                        </tr>
                      </thead>
                      <tbody>
                        {orderCartList.map((orderCart, i) => (
                          <tr key={`id-${i}`}>
                            <td>{orderCart.code}</td>
                            <td>{orderCart.shippingChinaCode}</td>
                            <td>{orderCart.createByLogin}</td>
                            <td>{formatCurency(orderCart.finalAmount)}đ</td>
                            <td>
                              <small>
                                <TextFormat type="date" value={orderCart.depositTime} format={APP_DATE_FORMAT} />
                              </small>
                            </td>
                            <td>
                              <small>
                                <span className="label label-warning">{orderCart.status}</span>
                              </small>
                            </td>
                            <td>
                              <Link to={`/staff/order-detail?orderid=${encodeId(orderCart.id)}`}>
                                <span className="label label-default">Chi tiết đơn hàng</span>
                              </Link>
                            </td>
                          </tr>
                        ))}
                      </tbody>
                      <tfoot>
                        <tr>
                          <td colSpan={7}>
                            <ul className="pagination pull-right">
                              <JhiPagination
                                items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
                                activePage={this.state.activePage}
                                onSelect={this.handlePagination}
                                maxButtons={5}
                              />
                            </ul>
                          </td>
                        </tr>
                      </tfoot>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div className="row">
            <div className="col-xs-12">
              <Footer />
            </div>
          </div>
        </div>
      </>
    );
  }
}

const mapStateToProps = ({ orderCart, authentication }: IRootState) => ({
  account: authentication.account,
  orderCartList: orderCart.entities,
  totalItems: orderCart.totalItems,
  isAuthenticated: authentication.isAuthenticated
});

const mapDispatchToProps = {
  getEntities,
  searchOrder,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OrderCart);
