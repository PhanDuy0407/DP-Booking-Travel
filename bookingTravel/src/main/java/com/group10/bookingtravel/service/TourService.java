package com.group10.bookingtravel.service;

import com.group10.bookingtravel.dto.*;
import com.group10.bookingtravel.entity.*;
import com.group10.bookingtravel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourPriceRepository tourPriceRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private LandTourPriceRepository landTourPriceRepository;

    @Autowired
    private TourScheduleRepository tourScheduleRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private GuideRepository guideRepository;

    public List<TourInfoDTO> getDataFromTour(String codeTour,String nameTour){
        if(codeTour == null || codeTour.equals("null")){
            codeTour = "";
        }
        if(nameTour == null || nameTour.equals("null")){
            nameTour = "";
        }
        return tourRepository.tourList(codeTour,nameTour);
    }

    public Price getPriceByIdTour(Long id){
        Optional<Price> price = priceRepository.getPriceById(id);
        if(price.isPresent()){
            return price.get();
        } else {
            return new Price();
        }
    }
    public Discount getDiscountPrice(Long id){
        Price p = this.getPriceByIdTour(id);
        if(p.getId() != null){
            Optional<Discount> discount = discountRepository.findByPriceId(p.getId());
            if(discount.isPresent()){
                return discount.get();
            } else{
                return new Discount();
            }
        }
        return new Discount();
    }
    public TourPrice getTourPriceByIdPrice(Long id){
        Price p = this.getPriceByIdTour(id);
        if(p.getTourPriceId() != null){
            Optional<TourPrice> tourPrice = tourPriceRepository.getTourPriceById(p.getTourPriceId());
            if(tourPrice.isPresent()){
               return tourPrice.get();
            } else {
              return new TourPrice();
            }
        }
        return new TourPrice();
    }

    public LandTourPrice getLandTourPriceByIdPrice(Long id){
        Price p = this.getPriceByIdTour(id);
        if(p.getLandTourPriceId() != null){
            Optional<LandTourPrice> landTourPrice = landTourPriceRepository.getLandTourPriceById(p.getLandTourPriceId());
            if(landTourPrice.isPresent()){
                return landTourPrice.get();
            } else {
                return new LandTourPrice();
            }
        }
        return new LandTourPrice();
    }

    public List<TourSchedule> getTourSchedulebyTourId(Long id){
        Optional<List<TourSchedule>> tourScheduleListOpt =  tourScheduleRepository.getTourScheduleByTourId(id);
        List<TourSchedule> tourScheduleList = new ArrayList<>();
        if(tourScheduleListOpt.isPresent()){
          return tourScheduleListOpt.get();
        } else {
            return tourScheduleList;
        }
    }

    public List<Orders> getListOrderByTourId(Long id){
        List<Orders> ordersList = new ArrayList<>();
        Price p = this.getPriceByIdTour(id);
        if(p.getId() != null){
            Optional<List<Orders>> listOrdersOpt = ordersRepository.listOrdersByPriceId(p.getId());
            if(listOrdersOpt.isPresent()){
                return listOrdersOpt.get();
            }
        }
        return ordersList;
    }
    public Integer getMaxIdTourSchedule(){
        return tourScheduleRepository.getMaxId();
    }

    public void addNewTour(DataAddTourDTO dataAddTourDTO) throws ParseException {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sm2 = new SimpleDateFormat("yyyy-MM-dd");

        Tour tour = new Tour();
        tour.setCode(dataAddTourDTO.getCode());
        tour.setName(dataAddTourDTO.getName());
        tour.setShortDesc(dataAddTourDTO.getShortDesc());
        Date startDate =new SimpleDateFormat("yyyy-MM-dd").parse(dataAddTourDTO.getStartTime());
        tour.setStartTime(startDate);
        tour.setPeriod(Integer.valueOf(dataAddTourDTO.getPeriod()));
        tour.setStartPlaceId(dataAddTourDTO.getStartPlaceId());
        tour.setEndPlaceId(dataAddTourDTO.getEndPlaceId());
        tour.setMainImageUrl(dataAddTourDTO.getMainImageUrl());
        tour.setGuideId(dataAddTourDTO.getGuideId());
        tour.setStatus(1);
        tourRepository.saveAndFlush(tour);

        TourPrice tourPrice = new TourPrice();
        if(dataAddTourDTO.getTourPriceAdult() == null || dataAddTourDTO.getTourPriceAdult() == "null"){
            tourPrice.setAdult(0);
        } else {
            tourPrice.setAdult(Integer.valueOf(dataAddTourDTO.getTourPriceAdult()));
        }
        if(dataAddTourDTO.getTourPriceChildren() == null || dataAddTourDTO.getTourPriceChildren() == "null"){
            tourPrice.setChildren(0);
        } else {
            tourPrice.setChildren(Integer.valueOf(dataAddTourDTO.getTourPriceChildren()));
        }
        if(dataAddTourDTO.getTourPriceKid() == null || dataAddTourDTO.getTourPriceKid() == "null"){
            tourPrice.setKid(0);
        } else {
            tourPrice.setKid(Integer.valueOf(dataAddTourDTO.getTourPriceKid()));
        }
        if(dataAddTourDTO.getTourPriceBaby() == null || dataAddTourDTO.getTourPriceBaby() == "null"){
            tourPrice.setBaby(0);
        } else {
            tourPrice.setBaby(Integer.valueOf(dataAddTourDTO.getTourPriceBaby()));
        }
        if(dataAddTourDTO.getTourPriceSurcharge() == null || dataAddTourDTO.getTourPriceSurcharge() == "null"){
            tourPrice.setSurcharge(0);
        } else {
            tourPrice.setSurcharge(Integer.valueOf(dataAddTourDTO.getTourPriceSurcharge()));
        }
        tourPriceRepository.saveAndFlush(tourPrice);

        LandTourPrice landTourPrice = new LandTourPrice();
        if(dataAddTourDTO.getLandtourPriceAdult() == null || dataAddTourDTO.getLandtourPriceAdult() == "null"){
            landTourPrice.setAdult(0);
        } else {
            landTourPrice.setAdult(Integer.valueOf(dataAddTourDTO.getLandtourPriceAdult()));
        }
        if(dataAddTourDTO.getLandtourPriceChildren() == null || dataAddTourDTO.getLandtourPriceChildren() == "null"){
            landTourPrice.setChildren(0);
        } else {
            landTourPrice.setChildren(Integer.valueOf(dataAddTourDTO.getLandtourPriceChildren()));
        }
        if(dataAddTourDTO.getLandtourPriceKid() == null || dataAddTourDTO.getLandtourPriceKid() == "null"){
            landTourPrice.setKid(0);
        } else {
            landTourPrice.setKid(Integer.valueOf(dataAddTourDTO.getLandtourPriceKid()));
        }
        if(dataAddTourDTO.getLandtourPriceBaby() == null || dataAddTourDTO.getLandtourPriceBaby() == "null"){
            landTourPrice.setBaby(0);
        } else {
            landTourPrice.setBaby(Integer.valueOf(dataAddTourDTO.getLandtourPriceBaby()));
        }
        if(dataAddTourDTO.getLandtourPriceSurcharge() == null || dataAddTourDTO.getLandtourPriceSurcharge() == "null"){
            landTourPrice.setSurcharge(0);
        } else {
            landTourPrice.setSurcharge(Integer.valueOf(dataAddTourDTO.getLandtourPriceSurcharge()));
        }
        landTourPriceRepository.saveAndFlush(landTourPrice);

        Price price = new Price();
        Long idTour = Long.valueOf(tourRepository.getMaxId());
        Long idLandTourPrice = Long.valueOf(landTourPriceRepository.getMaxId());
        Long idTourPrice = Long.valueOf(tourPriceRepository.getMaxId());

        price.setTourId(idTour);
        price.setTourPriceId(idTourPrice);
        price.setLandTourPriceId(idLandTourPrice);
        priceRepository.saveAndFlush(price);

        if((dataAddTourDTO.getDiscount() != null && dataAddTourDTO.getDiscount() != "null")&& (dataAddTourDTO.getDiscountStartDate() != null && dataAddTourDTO.getDiscountStartDate() != "null") && (dataAddTourDTO.getDiscountEndDate() != null && dataAddTourDTO.getDiscountEndDate() != "null")){
            Discount discount = new Discount();
            Long idPrice = priceRepository.getMaxId();
            discount.setPriceId(idPrice);
            discount.setDiscount(Float.valueOf(dataAddTourDTO.getDiscount()));
            Date discountStartDate =new SimpleDateFormat("yyyy-MM-dd").parse(dataAddTourDTO.getDiscountStartDate());
            Date discountEnDate =new SimpleDateFormat("yyyy-MM-dd").parse(dataAddTourDTO.getDiscountEndDate());
            discount.setStartDate(discountStartDate);
            discount.setEndDate(discountEnDate);
            discountRepository.saveAndFlush(discount);
        }
        for(TourScheduleDTO tourScheduleDTO: dataAddTourDTO.getTourScheduleDTOList()){
            TourSchedule tourSchedule = new TourSchedule();
            tourSchedule.setTourId(idTour);
            tourSchedule.setAlias(tourScheduleDTO.getAlias());
            Date scheduleTime =new SimpleDateFormat("yyyy-MM-dd").parse(tourScheduleDTO.getTime());
            tourSchedule.setTime(scheduleTime);
            tourSchedule.setLocation(tourScheduleDTO.getLocation());
            tourSchedule.setDetail(tourScheduleDTO.getDetail());
            tourScheduleRepository.saveAndFlush(tourSchedule);
        }
    }
    public void updateTour(DataUpdateTourDTO dataUpdateTourDTO) throws ParseException {

        Tour tour = new Tour();
        tour.setId(Long.valueOf(dataUpdateTourDTO.getId()));
        tour.setCode(dataUpdateTourDTO.getCode());
        tour.setName(dataUpdateTourDTO.getName());
        tour.setShortDesc(dataUpdateTourDTO.getShortDesc());
        Date startDate =new SimpleDateFormat("yyyy-MM-dd").parse(dataUpdateTourDTO.getStartTime());
        tour.setStartTime(startDate);
        tour.setPeriod(Integer.valueOf(dataUpdateTourDTO.getPeriod()));
        tour.setStartPlaceId(dataUpdateTourDTO.getStartPlaceId());
        tour.setEndPlaceId(dataUpdateTourDTO.getEndPlaceId());
        tour.setMainImageUrl(dataUpdateTourDTO.getMainImageUrl());
        tour.setGuideId(dataUpdateTourDTO.getGuideId());
        tour.setStatus(dataUpdateTourDTO.getStatus());
        tour.setPlaceOrderMax(Integer.valueOf(dataUpdateTourDTO.getPlaceOrderMax()));
        tourRepository.saveAndFlush(tour);

        TourPrice tourPrice = new TourPrice();
        tourPrice.setId(Long.valueOf(dataUpdateTourDTO.getIdTourPrice()));
        if(dataUpdateTourDTO.getTourPriceAdult() == null || dataUpdateTourDTO.getTourPriceAdult() == "null"){
            tourPrice.setAdult(0);
        } else {
            tourPrice.setAdult(Integer.valueOf(dataUpdateTourDTO.getTourPriceAdult()));
        }
        if(dataUpdateTourDTO.getTourPriceChildren() == null || dataUpdateTourDTO.getTourPriceChildren() == "null"){
            tourPrice.setChildren(0);
        } else {
            tourPrice.setChildren(Integer.valueOf(dataUpdateTourDTO.getTourPriceChildren()));
        }
        if(dataUpdateTourDTO.getTourPriceKid() == null || dataUpdateTourDTO.getTourPriceKid() == "null"){
            tourPrice.setKid(0);
        } else {
            tourPrice.setKid(Integer.valueOf(dataUpdateTourDTO.getTourPriceKid()));
        }
        if(dataUpdateTourDTO.getTourPriceBaby() == null || dataUpdateTourDTO.getTourPriceBaby() == "null"){
            tourPrice.setBaby(0);
        } else {
            tourPrice.setBaby(Integer.valueOf(dataUpdateTourDTO.getTourPriceBaby()));
        }
        if(dataUpdateTourDTO.getTourPriceSurcharge() == null || dataUpdateTourDTO.getTourPriceSurcharge() == "null"){
            tourPrice.setSurcharge(0);
        } else {
            tourPrice.setSurcharge(Integer.valueOf(dataUpdateTourDTO.getTourPriceSurcharge()));
        }
        tourPriceRepository.saveAndFlush(tourPrice);

        LandTourPrice landTourPrice = new LandTourPrice();
        landTourPrice.setId(Long.valueOf(dataUpdateTourDTO.getIdLandTourPrice()));
        if(dataUpdateTourDTO.getLandtourPriceAdult() == null || dataUpdateTourDTO.getLandtourPriceAdult() == "null"){
            landTourPrice.setAdult(0);
        } else {
            landTourPrice.setAdult(Integer.valueOf(dataUpdateTourDTO.getLandtourPriceAdult()));
        }
        if(dataUpdateTourDTO.getLandtourPriceChildren() == null || dataUpdateTourDTO.getLandtourPriceChildren() == "null"){
            landTourPrice.setChildren(0);
        } else {
            landTourPrice.setChildren(Integer.valueOf(dataUpdateTourDTO.getLandtourPriceChildren()));
        }
        if(dataUpdateTourDTO.getLandtourPriceKid() == null || dataUpdateTourDTO.getLandtourPriceKid() == "null"){
            landTourPrice.setKid(0);
        } else {
            landTourPrice.setKid(Integer.valueOf(dataUpdateTourDTO.getLandtourPriceKid()));
        }
        if(dataUpdateTourDTO.getLandtourPriceBaby() == null || dataUpdateTourDTO.getLandtourPriceBaby() == "null"){
            landTourPrice.setBaby(0);
        } else {
            landTourPrice.setBaby(Integer.valueOf(dataUpdateTourDTO.getLandtourPriceBaby()));
        }
        if(dataUpdateTourDTO.getLandtourPriceSurcharge() == null || dataUpdateTourDTO.getLandtourPriceSurcharge() == "null"){
            landTourPrice.setSurcharge(0);
        } else {
            landTourPrice.setSurcharge(Integer.valueOf(dataUpdateTourDTO.getLandtourPriceSurcharge()));
        }
        landTourPriceRepository.saveAndFlush(landTourPrice);

//        Price price = new Price();
//        price.setId(idPrice);
//        price.setTourId(idTour);
//        price.setTourPriceId(idTourPrice);
//        price.setLandTourPriceId(idLandTourPrice);
//        priceRepository.saveAndFlush(price);

        if((dataUpdateTourDTO.getDiscount() != null && dataUpdateTourDTO.getDiscount() != "null")&& (dataUpdateTourDTO.getDiscountStartDate() != null && dataUpdateTourDTO.getDiscountStartDate() != "null") && (dataUpdateTourDTO.getDiscountEndDate() != null && dataUpdateTourDTO.getDiscountEndDate() != "null")){
            Discount discount = new Discount();
            discount.setPriceId(Long.valueOf(dataUpdateTourDTO.getPriceId()));
            discount.setDiscount(Float.valueOf(dataUpdateTourDTO.getDiscount()));
            Date discountStartDate =new SimpleDateFormat("yyyy-MM-dd").parse(dataUpdateTourDTO.getDiscountStartDate());
            Date discountEnDate =new SimpleDateFormat("yyyy-MM-dd").parse(dataUpdateTourDTO.getDiscountEndDate());
            discount.setStartDate(discountStartDate);
            discount.setEndDate(discountEnDate);
            discountRepository.saveAndFlush(discount);
        }
        tourScheduleRepository.deleteAllByTourId(Long.valueOf(dataUpdateTourDTO.getId()));
        for(TourScheduleUpdateDTO tourScheduleDTO: dataUpdateTourDTO.getTourScheduleDTOList()){
            TourSchedule tourSchedule = new TourSchedule();
            tourSchedule.setTourId(Long.valueOf(tourScheduleDTO.getTourId()));
            tourSchedule.setAlias(tourScheduleDTO.getAlias());
            Date scheduleTime =new SimpleDateFormat("yyyy-MM-dd").parse(tourScheduleDTO.getTime());
            tourSchedule.setTime(scheduleTime);
            tourSchedule.setLocation(tourScheduleDTO.getLocation());
            tourSchedule.setDetail(tourScheduleDTO.getDetail());
            tourScheduleRepository.saveAndFlush(tourSchedule);
        }
    }

    // get tour list for frontend user
    public List<TourInfoDTO> getTourList(){
        return tourRepository.getTourList();
    }
    public List<TourInfoDTO> getTourListFilter(Long startPLaceId, Long endPlaceId, Date startTime, Integer fromPeriod, Integer toPeriod, Integer fromPrice, Integer toPrice){
        return tourRepository.getTourListFilter(startPLaceId, endPlaceId, startTime, fromPeriod, toPeriod, fromPrice, toPrice);
    }
    public TourDTO_User getTourDetail(Long id){
        TourDTO_User tour = tourRepository.getTourById(id);
        Optional<TourPrice> tourPriceOptional = tourPriceRepository.getTourPriceByTourId(id);
        tourPriceOptional.ifPresent(tour::setTourPrice);
        Optional<LandTourPrice> landTourPriceOptional = landTourPriceRepository.getLandTourPriceByTourId(id);
        landTourPriceOptional.ifPresent(tour::setLandTourPrice);
        Optional<List<TourSchedule>> tourScheduleListOpt = tourScheduleRepository.getTourScheduleByTourId(id);
        tourScheduleListOpt.ifPresent(tour::setTourScheduleList);
        return tour;
    }
    public List<TourDTO_User> getToursDiscount(){
        return tourRepository.getToursDiscount();
    }
}
