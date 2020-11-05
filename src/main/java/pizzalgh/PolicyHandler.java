package pizzalgh;

import pizzalgh.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired
    CouponRepository couponRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverWritten_PublishCoupon(@Payload Written written){

        if(written.isMe()){
            System.out.println("##### listener PublishCoupon : " + written.toJson());
            Coupon coupon = new Coupon();
            coupon.setOrderId(written.getOrderedId());


            couponRepository.save(coupon);
        }
    }

}
