/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.MileageRateDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.MileageRate;

/**
 *
 * @author koenv
 */
@Stateless
public class MileageRateService implements IMileageRateService{
    @EJB
    MileageRateDAO mrd;
    
    @Override
    public void createMileageRate(MileageRate mr)
    {
        mrd.createMileageRate(mr);
    }
    
    @Override
    public List<MileageRate> getAllRates() 
    {
        return mrd.getAllRates();
    }
    
    @Override
    public List<MileageRate> getRateByPrizeCategory(double prizecategory)
    {
        return mrd.getRatePerPrizeCategory(prizecategory);
    }
    
    @Override
    public List<MileageRate> getRateByInterval(double interval) 
    {
        return mrd.getRatePerInterval(interval);
    }

    @Override
    public List<MileageRate> getRateByRegio(String regio)
    {
        return mrd.getRatePerArea(regio);
    }
    
    @Override
    public void changeMileageRate(MileageRate mr, double mar)
    {
        mrd.changeRate(mr, mar);
    }
    
    @Override
    public void changeRegio(MileageRate mr, String regio)
    {
        mrd.changeRegio(mr, regio);
    }
    
    @Override
    public void changePrizeCategory(MileageRate mr, double prizecategory)
    {
        mrd.changePrizeCategory(mr, prizecategory);
    }
    
    @Override
    public void changeInterval(MileageRate mr, double interval)
    {
        mrd.changeInterval(mr, interval);
    }
}
