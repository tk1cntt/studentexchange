import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
// tslint:disable-next-line:no-unused-variable
import { TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import qs from 'query-string';
import { formatCurency, encodeId, orderQueryStringMapping } from 'app/shared/util/utils';

import Header from 'app/shared/layout/header/header';
import Footer from 'app/shared/layout/footer/footer';
import Sidebar from 'app/shared/layout/sidebar/sidebar';
import OrderSearchBox from 'app/shared/layout/order/order-search-box';

import { IRootState } from 'app/shared/reducers';
import { getEntities, searchOrder, reset } from 'app/entities/order-cart/order-cart.reducer';
import { OrderStatus } from 'app/shared/model/order-cart.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IOrderCartProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IOrderCartState = IPaginationBaseState;

export class OrderCart extends React.Component<IOrderCartProps, IOrderCartState> {
  state: IOrderCartState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    if (this.props.location) {
      const parsed = qs.parse(this.props.location.search);
      this.getEntities(orderQueryStringMapping(parsed));
    }
  }

  componentDidUpdate(prevProps) {
    // Typical usage (don't forget to compare props):
    if (this.props.location !== prevProps.location) {
      const parsed = qs.parse(this.props.location.search);
      this.getEntities(orderQueryStringMapping(parsed));
    }
  }

  componentWillUnmount() {
    this.props.reset();
  }

  handlePagination = activePage => this.setState({ activePage });

  getEntities = query => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.searchOrder(query, activePage - 1, itemsPerPage, `createAt,desc`);
    // this.props.getEntities(activePage - 1, itemsPerPage, `createAt,asc`);
  };

  selectOrderStatus = () => {};

  render() {
    const { orderCartList, match, totalItems } = this.props;
    return (
      <>
        <Sidebar activeMenu="staff" activeSubMenu="order-list" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row  border-bottom white-bg dashboard-header">
            <h3>Danh sách đơn hàng</h3>
          </div>
          <div className="wrapper wrapper-content animated fadeInRight ecommerce">
            <OrderSearchBox to="/staff/order-list" location={this.props.location} history={this.props.history} />
            <div className="row">
              <div className="col-lg-12">
                <div className="ibox">
                  <div className="ibox-content">
                    <table className="footable table table-stripped toggle-arrow-tiny tablet breakpoint footable-loaded">
                      <thead>
                        <tr>
                          <th>Mã đơn hàng</th>
                          <th>Mã vận đơn</th>
                          <th>
                            <i className="fa fa-user" /> Khách hàng
                          </th>
                          <th>
                            Tiền cọc <b className="text-danger">(70%)</b>
                          </th>
                          <th>Ngày đặt</th>
                          <th />
                        </tr>
                      </thead>
                      <tbody>
                        {orderCartList.map((orderCart, i) => (
                          <tr key={`id-${i}`}>
                            <td>{orderCart.code}</td>
                            <td>
                              {orderCart.status === OrderStatus.DEPOSITED || orderCart.status === OrderStatus.ARE_BUYING ? (
                                <small className="badge">Chưa mua hàng</small>
                              ) : orderCart.status === OrderStatus.CANCELLED ? (
                                <small className="badge badge-danger">Đã huỷ</small>
                              ) : (
                                <>
                                  {orderCart.shippingChinaCode}
                                  <br />
                                  <small className="text-warning">
                                    <b>{orderCart.website}</b>
                                  </small>
                                </>
                              )}
                            </td>
                            <td>
                              {orderCart.receiverName}
                              <br />
                              <i className="fa fa-phone" /> {orderCart.receiverMobile}
                            </td>
                            <td>{formatCurency(orderCart.depositAmount)}đ</td>
                            <td>
                              <small>
                                <TextFormat type="date" value={orderCart.depositTime} format={APP_DATE_FORMAT} />
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
