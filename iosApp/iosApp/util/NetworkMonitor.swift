//
//  NetworkMonitor.swift
//  iosApp
//
//  Created by Selvakumar SM on 07/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Network

final class NetworkMonitor: ObservableObject {
    
    @Published private(set) var isConnected = false
    
    private let nwMonitor = NWPathMonitor()
    private let workerQueue = DispatchQueue.global()
    
    public func start() {
        nwMonitor.start(queue: workerQueue)
        nwMonitor.pathUpdateHandler = { [weak self] path in
            DispatchQueue.main.async {
                self?.isConnected = path.status == .satisfied
            }
        }
    }
    
    public func stop() {
        nwMonitor.cancel()
    }
}
