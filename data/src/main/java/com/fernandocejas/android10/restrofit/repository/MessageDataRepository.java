/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.restrofit.repository;

import com.fernandocejas.android10.order.domain.Message;
import com.fernandocejas.android10.order.domain.repository.MessageRepository;
import com.fernandocejas.android10.restrofit.enity.mapper.MessageEntityDataMapper;
import com.fernandocejas.android10.restrofit.net.RetrofitHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class MessageDataRepository implements MessageRepository {

    private final RetrofitHelper retrofitHelper;

    private final MessageEntityDataMapper messageEntityDataMapper;

    @Inject
    MessageDataRepository(
            RetrofitHelper retrofitHelper,
            MessageEntityDataMapper messageEntityDataMapper) {
        this.retrofitHelper = retrofitHelper;
        this.messageEntityDataMapper = messageEntityDataMapper;
    }


    @Override
    public Observable<Message> message_send(String token,
                                            String order,
                                            String receiver,
                                            String message,
                                            String type) {
        return retrofitHelper
                .getRestApiService()
                .message_send("Bearer " + token, order, receiver, message, type)
                .map(this.messageEntityDataMapper::transform);
    }
}
