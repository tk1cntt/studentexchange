import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
// tslint:disable-next-line:no-unused-variable
import { TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import qs from 'query-string';
import { formatCurency, encodeId } from 'app/shared/util/utils';

import Header from 'app/shared/layout/header/header';
import Footer from 'app/shared/layout/footer/footer';
import Sidebar from 'app/shared/layout/sidebar/sidebar';

import { IRootState } from 'app/shared/reducers';
import { getEntities, searchOrder, updateBuying, reset } from 'app/entities/order-cart/order-cart.reducer';
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
      this.getEntities('status.equals=DEPOSITED');
      // this.props.getHouses(queryStringMapping(parsed));
    }
  }

  componentDidUpdate(prevProps) {
    // Typical usage (don't forget to compare props):
    if (this.props.location !== prevProps.location) {
      const parsed = qs.parse(this.props.location.search);
      // this.props.getHouses(queryStringMapping(parsed));
      this.getEntities('status.equals=DEPOSITED');
    }
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
    this.getEntities('status.equals=DEPOSITED');
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=createAt,asc`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = query => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.searchOrder(query, activePage - 1, itemsPerPage, `createAt,asc`);
    // this.props.getEntities(activePage - 1, itemsPerPage, `createAt,asc`);
  };

  receiveOrderClick = id => {
    this.props.updateBuying({ id });
    this.props.history.push(`/staff/buying?orderid=${encodeId(id)}`);
  };

  onChangeCode = () => {};

  searchClick = () => {};

  render() {
    const { orderCartList, match, totalItems } = this.props;
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="order-management" activeSubMenu="order-deposited" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row  border-bottom white-bg dashboard-header">
            <h3>Danh sách đơn hàng chưa được xử lý</h3>
          </div>
          <div className="wrapper wrapper-content animated fadeInRight ecommerce">
            <div className="row">
              <div className="col-lg-12">
                <div className="ibox">
                  <div className="ibox-content">
                    <table className="footable table table-stripped toggle-arrow-tiny tablet breakpoint footable-loaded">
                      <thead>
                        <tr>
                          <th>Mã đơn hàng</th>
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
                              <span className="label label-info" onClick={this.receiveOrderClick.bind(this, orderCart.id)}>
                                <i className="fa fa-angle-double-right" /> Nhận xử lý đơn hàng
                              </span>
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
  orderCartList: orderCart.entities,
  totalItems: orderCart.totalItems,
  isAuthenticated: authentication.isAuthenticated
});

const mapDispatchToProps = {
  getEntities,
  searchOrder,
  updateBuying,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(OrderCart);
