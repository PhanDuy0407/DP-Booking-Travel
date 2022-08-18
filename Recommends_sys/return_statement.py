from unittest import result
from fastapi import FastAPI
import recommend

app = FastAPI()


@app.get("/recommend")
async def root(userId: int):
    result = recommend.topNRecommendations(userId, 6)
    print(result)
    return result