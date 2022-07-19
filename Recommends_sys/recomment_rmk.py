# -*- coding: utf-8 -*-
"""
Original file is located at
    https://colab.research.google.com/drive/1MJEutTzWCLcJylWKL7FTR_Rgx_gQCVzf
"""

#creat data
#import pandas as pd
#import numpy as np
#data1 = {'userId': [10]*32,
#        'itemId': np.arange(1,33),
 #        'rating':np.random.randint(0,5,32)
 #       }

#df = pd.DataFrame(data1, index=None)

#print (df)

import numpy as np
import pandas as pd
import scipy.sparse
from scipy.spatial.distance import correlation

data=pd.read_csv('/content/data_collaborative_1.csv')
placeInfo=pd.read_csv('/content/rmk.csv')
#data=pd.concat([data,df])  #update data
#userIds=data.userId
#userIds2=data[['userId']]

data.loc[0:10,['userId']]
data=pd.DataFrame.sort_values(data,['userId','itemId'],ascending=[0,1])

def favoritePlace(activeUser,N):
    topPlace=pd.DataFrame.sort_values(
        data[data.userId==activeUser],['rating'],ascending=[0])[:N]
    return list(topPlace.itemId)

userItemRatingMatrix=pd.pivot_table(data, values='rating',
                                    index=['userId'], columns=['itemId'])

def similarity(user1,user2):
    try:
        user1=np.array(user1)-np.nanmean(user1)#rate dau-rate trung binh
        user2=np.array(user2)-np.nanmean(user2)
        commonItemIds=[i for i in range(len(user1)) if user1[i]>0 and user2[i]>0]#tim cac item ma ca user key voi user khac cung thich(rate>0)
        if len(commonItemIds)==0:
           return 0
        else:
           user1=np.array([user1[i] for i in commonItemIds])
           user2=np.array([user2[i] for i in commonItemIds])
           return correlation(user1,user2)#tinh phuong sai the hien moi tuong quan 2 user,dung scipy.spatial.distance nen gia tri[0,2]
    except ZeroDivisionError:
        print("You can't divide by zero!")

#ham tinh cac neighbour ratings gan voi user rate nhat 
def nearestNeighbourRatings(activeUser,K):
    try:
        similarityMatrix=pd.DataFrame(index=userItemRatingMatrix.index,columns=['Similarity'])#tao matrix voi cot user id cua data va similarity NaN
       
        #print(similarityMatrix)
        #print(userItemRatingMatrix)
        for i in userItemRatingMatrix.index:
            similarityMatrix.loc[i]=similarity(userItemRatingMatrix.loc[activeUser],userItemRatingMatrix.loc[i])#chay ham similarity 
        similarityMatrix=pd.DataFrame.sort_values(similarityMatrix,['Similarity'],ascending=[0])
       # print(similarityMatrix)
        nearestNeighbours=similarityMatrix[:K]
        neighbourItemRatings=userItemRatingMatrix.loc[nearestNeighbours.index]
        #print(neighbourItemRatings)
        predictItemRating=pd.DataFrame(index=userItemRatingMatrix.columns, columns=['Rating'])
       # print(predictItemRating)
        for i in userItemRatingMatrix.columns:
            predictedRating=np.nanmean(userItemRatingMatrix.loc[activeUser])
            for j in neighbourItemRatings.index:
                if userItemRatingMatrix.loc[j,i]>0:
                   predictedRating += (userItemRatingMatrix.loc[j,i]-np.nanmean(userItemRatingMatrix.loc[j]))*nearestNeighbours.loc[j,'Similarity']
                predictItemRating.loc[i,'Rating']=predictedRating
       # print(predictItemRating)

    except ZeroDivisionError:
        print("You can't divide by zero!")            
    return predictItemRating

def topNRecommendations(activeUser,N):
    try:
        predictItemRating=nearestNeighbourRatings(activeUser,10)
        placeAlreadyWatched=list(userItemRatingMatrix.loc[activeUser]
                              .loc[userItemRatingMatrix.loc[activeUser]>0].index)
        predictItemRating=predictItemRating.drop(placeAlreadyWatched)
        topRecommendations=pd.DataFrame.sort_values(predictItemRating,
                                                ['Rating'],ascending=[0])[:N]
        topRecommendationTitles=(placeInfo.loc[placeInfo.itemId.isin(topRecommendations.index)])
    except ZeroDivisionError:
        print("You can't divide by zero!")
    return list(topRecommendationTitles.itemId)

activeUser=int(input("Enter userid: "))
#print("The user's favorite places are: ")
#print(favoritePlace(activeUser,5))
print("The recommended places for you are: ")
print(topNRecommendations(activeUser,4))

#khi loại bỏ item,chỉ cần gán nhãn cho user đang xét đánh giá item đó là số >0 sẽ đảm bảo ko gợi ý sản phẩm đó
#khi thêm sản phẩm,thêm vào bình thường.
#các người dùng chưa đánh giá sản phẩm nào thì gán rate đó là  0
