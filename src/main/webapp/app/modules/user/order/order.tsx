import React from 'react';
import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';
import { TextFormat } from 'react-jhipster';

import { getSession } from 'app/shared/reducers/authentication';
import { getOwnerEntities } from 'app/entities/order-cart/order-cart.reducer';
import { APP_DATE_FORMAT } from 'app/config/constants';

import Header from 'app/shared/layout/header/header';
import Footer from 'app/shared/layout/footer/footer';
import Sidebar from 'app/shared/layout/sidebar/sidebar';

export interface IHomeProp extends StateProps, DispatchProps {}

export class Order extends React.Component<IHomeProp> {
  componentDidMount() {
    this.props.getOwnerEntities();
  }

  render() {
    const { orderCartList } = this.props;
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="order-cart" activeSubMenu="" />
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
                    <input type="text" id="order_id" name="order_id" placeholder="Order ID" className="form-control" />
                  </div>
                </div>
                <div className="col-sm-4">
                  <div className="form-group">
                    <label className="control-label" htmlFor="status">
                      Trạng thái đơn hàng
                    </label>
                    <input type="text" id="status" name="status" placeholder="Status" className="form-control" />
                  </div>
                </div>
                <div className="col-sm-4">
                  <div className="form-group">
                    <label className="control-label" htmlFor="status" />
                    <button className="btn btn-primary btn-block">
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
                          <th>Shop name</th>
                          <th>Tổng tiền</th>
                          <th>Ngày mua</th>
                          <th>Trạng thái</th>
                        </tr>
                      </thead>
                      <tbody>
                        {orderCartList.map((orderCart, i) => (
                          <tr key={`id-${i}`}>
                            <td>{orderCart.code}</td>
                            <td>{orderCart.shopName}</td>
                            <td>{orderCart.finalAmount}</td>
                            <td>
                              <small>
                                <TextFormat type="date" value={orderCart.depositTime} format={APP_DATE_FORMAT} />
                              </small>
                            </td>
                            <td>
                              <span className="label label-primary">Đã đặt cọc</span>
                            </td>
                          </tr>
                        ))}
                      </tbody>
                      <tfoot>
                        <tr>
                          <td colSpan={7}>
                            <ul className="pagination pull-right">
                              <li className="footable-page-arrow disabled">
                                <a data-page="first" href="#first">
                                  «
                                </a>
                              </li>
                              <li className="footable-page-arrow disabled">
                                <a data-page="prev" href="#prev">
                                  ‹
                                </a>
                              </li>
                              <li className="footable-page active">
                                <a data-page={0} href="#">
                                  1
                                </a>
                              </li>
                              <li className="footable-page">
                                <a data-page={1} href="#">
                                  2
                                </a>
                              </li>
                              <li className="footable-page">
                                <a data-page={2} href="#">
                                  3
                                </a>
                              </li>
                              <li className="footable-page-arrow">
                                <a data-page="next" href="#next">
                                  ›
                                </a>
                              </li>
                              <li className="footable-page-arrow">
                                <a data-page="last" href="#last">
                                  »
                                </a>
                              </li>
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

const mapStateToProps = storeState => ({
  orderCartList: storeState.orderCart.entities,
  isAuthenticated: storeState.authentication.isAuthenticated
});

const mapDispatchToProps = { getSession, getOwnerEntities };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Order);
